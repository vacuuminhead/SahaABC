package com.example.user.sahaabcv11;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Lesson7 extends Lesson {
    private ArrayList<String> leftSyl;
    private ArrayList<String> rightSyl;
    private int[] leftSound;
    private int[] rightSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_l_s);

        String[] syll = getValue.getSyllables(position);
        String let = getResources().getStringArray(R.array.alphabet)[position];
        String[] plusL;
        if (position<8 || (position>22 && position<27) || (position>30 && position<35))
            plusL = getResources().getStringArray(R.array.consonant);
        else
            plusL = getResources().getStringArray(R.array.vowels);

        findViewById(R.id.menu).setOnClickListener(onClickListener);
        findViewById(R.id.back).setOnClickListener(onClickListener);
        findViewById(R.id.forward).setOnClickListener(onClickListener);

        leftSyl = new ArrayList<>();
        rightSyl = new ArrayList<>();

        TextView textView = (TextView)findViewById(R.id.letter);
        textView.setText(let);
        textView.setTextColor(getValue.getColor(let));
        textView.setBackgroundColor(Color.TRANSPARENT);
        textView.setOnLongClickListener(myOnLongClickListener);

        LinearLayout linearLayout1 = (LinearLayout)findViewById(R.id.left);
        LinearLayout linearLayout2 = (LinearLayout)findViewById(R.id.right);
        int size = getResources().getInteger(R.integer.lesson7_text_size);
        int count1 = 0;
        int count2 = 0;
        for (int i=0;i<plusL.length;i++){
            if (Arrays.asList(syll).contains(plusL[i]+ let)){
                TextView textView1 = new TextView(this);
                textView1.setText(plusL[i]);
                textView1.setTag(count1);
                count1++;
                leftSyl.add(plusL[i]+ let);
                textView1.setTextColor(Color.BLACK);
                textView1.setGravity(Gravity.CENTER);
                textView1.setTextSize(size);
                textView1.setOnDragListener(dragListener1);
                linearLayout1.addView(textView1);
            }
            if (Arrays.asList(syll).contains(let +plusL[i])){
                TextView textView1 = new TextView(this);
                textView1.setText(plusL[i]);
                textView1.setTag(count2);
                count2++;
                rightSyl.add(let +plusL[i]);
                textView1.setTextColor(Color.BLACK);
                textView1.setGravity(Gravity.CENTER);
                textView1.setTextSize(size);
                textView1.setOnDragListener(dragListener2);
                linearLayout2.addView(textView1);
            }
        }

        lessonSound = R.raw.lesson7;
        if (position>41) bClass = Lesson1.class;
        else bClass = Lesson4.class;
        if(position==31 || position == 42) nClass = Lesson9.class;
        else nClass = Lesson8.class;
    }

    public void onResume(){
        super.onResume();
        if (leftSyl.size()!=0) {
            leftSound = new int[leftSyl.size()];
            for (int i = 0; i < leftSyl.size(); i++)
                leftSound[i] = sound.load(this, getValue.getStringSound(leftSyl.get(i)), 1);
        }
        if (rightSyl.size()!=0){
            rightSound = new int[rightSyl.size()];
            for (int i = 0;i<rightSyl.size();i++)
                rightSound[i]=sound.load(this,getValue.getStringSound(rightSyl.get(i)),1);
        }
    }

    View.OnLongClickListener myOnLongClickListener = new View.OnLongClickListener(){
        @Override
        public boolean onLongClick(View v){
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v,0);
            return false;
        }
    };

    View.OnDragListener dragListener1 = new View.OnDragListener(){
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()){
                case DragEvent.ACTION_DRAG_STARTED: break;
                case DragEvent.ACTION_DRAG_ENTERED: v.setBackgroundColor(Color.GREEN);
                    break;
                case DragEvent.ACTION_DRAG_EXITED: v.setBackgroundColor(Color.TRANSPARENT);
                    break;
                case DragEvent.ACTION_DROP:
                    TextView view = (TextView) event.getLocalState();
                    TextView container = (TextView) v;
                    String s = container.getText().toString() + view.getText().toString();
                    container.setText(s);
                    container.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int p = (int)((TextView)v).getTag();
                            sound.play(leftSound[p],1,1,1,0,1);
                        }
                    });
                    v.setOnDragListener(null);
                    v.setBackgroundColor(Color.TRANSPARENT);
                    break;
                case DragEvent.ACTION_DRAG_ENDED: break;
                default: break;
            }
            return true;
        }
    };

    View.OnDragListener dragListener2 =new View.OnDragListener(){
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()){
                case DragEvent.ACTION_DRAG_STARTED: break;
                case DragEvent.ACTION_DRAG_ENTERED: v.setBackgroundColor(Color.GREEN);
                    break;
                case DragEvent.ACTION_DRAG_EXITED: v.setBackgroundColor(Color.TRANSPARENT);
                    break;
                case DragEvent.ACTION_DROP:
                    TextView view = (TextView) event.getLocalState();
                    TextView container = (TextView) v;
                    String s = view.getText().toString() + container.getText().toString();
                    container.setText(s);
                    container.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int p = (int)((TextView)v).getTag();
                            sound.play(rightSound[p],1,1,1,0,1);
                        }
                    });
                    v.setOnDragListener(null);
                    v.setBackgroundColor(Color.TRANSPARENT);
                    break;
                case DragEvent.ACTION_DRAG_ENDED: break;
                default: break;
            }
            return true;
        }
    };
}
