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

public class Lesson8 extends Lesson {
    private ArrayList<String> leftSyl;
    private ArrayList<String> rightSyl;
    private ArrayList<String> midSyl;
    private int[] leftSound;
    private int[] rightSound;
    private int[] midSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_l_s);

        String[] syll = getValue.getSyllables(position);
        String let = getResources().getStringArray(R.array.alphabet)[position];
        String[] plusSyl = getValue.getPartSyllables(position);

        findViewById(R.id.menu).setOnClickListener(onClickListener);
        findViewById(R.id.back).setOnClickListener(onClickListener);
        findViewById(R.id.forward).setOnClickListener(onClickListener);

        leftSyl = new ArrayList<>();
        rightSyl = new ArrayList<>();
        midSyl = new ArrayList<>();

        TextView textView = (TextView)findViewById(R.id.letter);
        textView.setText(let);
        textView.setTextColor(getValue.getColor(let));
        textView.setBackgroundColor(Color.TRANSPARENT);
        textView.setOnLongClickListener(myOnLongClickListener);

        LinearLayout linearLayout1 = (LinearLayout)findViewById(R.id.left);
        LinearLayout linearLayout2 = (LinearLayout)findViewById(R.id.right);
        int size = getResources().getInteger(R.integer.lesson8_text_size);
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        if ((position>22 && position<27) || (position>30 && position<35 || position==43)){
            for (int i=0;i<plusSyl.length;i++){
                TextView textView1 = new TextView(this);
                textView1.setText(plusSyl[i]);
                textView1.setTag(count3);
                count3++;
                midSyl.add(plusSyl[i].charAt(0)+let+plusSyl[i].subSequence(1,plusSyl[i].length()));
                textView1.setTextColor(Color.BLACK);
                textView1.setGravity(Gravity.CENTER);
                textView1.setTextSize(size);
                textView1.setOnDragListener(dragListener3);
                linearLayout1.addView(textView1);
                }
        } else
        {
            for (int i=0;i<plusSyl.length;i++){
                if (Arrays.asList(syll).contains(plusSyl[i]+ let)){
                    TextView textView1 = new TextView(this);
                    textView1.setText(plusSyl[i]);
                    textView1.setTag(count1);
                    count1++;
                    leftSyl.add(plusSyl[i]+ let);
                    textView1.setTextColor(Color.BLACK);
                    textView1.setGravity(Gravity.CENTER);
                    textView1.setTextSize(size);
                    textView1.setOnDragListener(dragListener1);
                    linearLayout1.addView(textView1);
                }
                if (Arrays.asList(syll).contains(let +plusSyl[i])){
                    TextView textView1 = new TextView(this);
                    textView1.setText(plusSyl[i]);
                    textView1.setTag(count2);
                    count2++;
                    rightSyl.add(let +plusSyl[i]);
                    textView1.setTextColor(Color.BLACK);
                    textView1.setGravity(Gravity.CENTER);
                    textView1.setTextSize(size);
                    textView1.setOnDragListener(dragListener2);
                    linearLayout2.addView(textView1);
                }
            }
        }
        lessonSound = R.raw.lesson7;
        if (position == 43) bClass = Lesson1.class;
        else bClass = Lesson7.class;
        if (position>41) nClass = Lesson10.class;
        else nClass = Lesson9.class;
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
        if (midSyl.size()!=0){
            midSound = new int[midSyl.size()];
            for (int i = 0;i<midSyl.size();i++)
                midSound[i]=sound.load(this,getValue.getStringSound(midSyl.get(i)),1);
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

    View.OnDragListener dragListener1 = new View.OnDragListener() {
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
                    String s = container.getText().toString()+view.getText().toString();
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

    View.OnDragListener dragListener2 = new View.OnDragListener(){
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

    View.OnDragListener dragListener3 =new View.OnDragListener() {
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
                    String s = container.getText().toString();
                    String s2 = s.charAt(0)+ view.getText().toString()+s.subSequence(1,s.length());
                    container.setText(s2);
                    container.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int p = (int)((TextView)v).getTag();
                            sound.play(midSound[p],1,1,1,0,1);
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
