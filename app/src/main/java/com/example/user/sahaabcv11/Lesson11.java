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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Lesson11 extends Lesson {
    public static int count;
    private int wordsound;
    private Random random;
    private int[] words;
    private int[] wSounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_i_w);

        count=0;
        random = new Random();
        words = getValue.getWords(position);

        findViewById(R.id.menu).setOnClickListener(onClickListener);
        findViewById(R.id.back).setOnClickListener(onClickListener);
        findViewById(R.id.reboot).setOnClickListener(onClickListener1);

        lessonSound = R.raw.lesson11;
        bClass = Lesson10.class;
    }

    public void onResume(){
        super.onResume();
        reboot();
    }
    private void reboot(){
        String[] wordsas = getResources().getStringArray(words[random.nextInt(words.length)]);

        String word = wordsas[0];
        if (wordsound!=0){
            sound.unload(wordsound);
        }
        wordsound = sound.load(getApplicationContext(),getValue.getStringSound(word),1);

        Set<String> generator = new HashSet<>();
        for (int i=2;i<wordsas.length;i++){
            generator.add(wordsas[i]);
        }

        while (generator.size()<6){
            String[] p = getValue.getSyllables(8+random.nextInt(35));
            String p1 = p[random.nextInt(p.length)];
            if (getValue.getStringSound(p1)!=0)
            generator.add(p1);
        }
        String[] wariations = generator.toArray(new String[generator.size()]);
        wSounds = new int[6];
        ImageView imageView = (ImageView)findViewById(R.id.ill);
        imageView.setImageResource(getResources().getIdentifier(wordsas[1],"drawable",getPackageName()));
        imageView.setOnClickListener(onClickListener1);

        int size = getResources().getInteger(R.integer.lesson10_text_size);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.options);
        linearLayout.removeAllViewsInLayout();
        for (int i = 0; i< wariations.length; i++){
            TextView textView = new TextView(this);
            textView.setText(wariations[i]);
            wSounds[i] = sound.load(this,getValue.getStringSound(wariations[i]),1);
            textView.setTextColor(Color.BLACK);
            textView.setTag(i);
            textView.setTextSize(size);
            textView.setGravity(Gravity.CENTER);
            textView.setOnLongClickListener(onLongClickListener);
            linearLayout.addView(textView);
        }

        LinearLayout linearLayout1 = (LinearLayout)findViewById(R.id.answers);
        linearLayout1.removeAllViewsInLayout();
        for (int i=2;i<wordsas.length;i++){
            count++;
            TextView textView = new TextView(this);
            textView.setText(wordsas[i]);
            textView.setTextColor(Color.TRANSPARENT);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 0, 10, 0);
            textView.setLayoutParams(params);
            textView.setBackgroundColor(Color.LTGRAY);
            textView.setTextSize(size);
            textView.setGravity(Gravity.CENTER);
            textView.setOnDragListener(onDragListener);
            linearLayout1.addView(textView);
        }
    }

    View.OnDragListener onDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()){
                case DragEvent.ACTION_DRAG_STARTED: break;
                case DragEvent.ACTION_DRAG_ENTERED: v.setBackgroundColor(Color.GREEN);
                    break;
                case DragEvent.ACTION_DRAG_EXITED: v.setBackgroundColor(Color.LTGRAY);
                    break;
                case DragEvent.ACTION_DROP:
                    TextView view = (TextView) event.getLocalState();
                    TextView container = (TextView) v;
                    String s = container.getText().toString();
                    String s2 = view.getText().toString();
                    if (s.equals(s2)) {
                        container.setTextColor(Color.BLACK);
                        v.setOnDragListener(null);
                        v.setBackgroundColor(Color.TRANSPARENT);
                        Lesson11.count--;
                    }
                    if (Lesson11.count==0){
                        sound.play(wordsound,1,1,1,0,1);
                        findViewById(R.id.reboot).setVisibility(View.VISIBLE);
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED: v.setBackgroundColor(Color.LTGRAY);
                    break;
                default: break;
            }
            return true;
        }
    };

    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener(){
        @Override
        public boolean onLongClick(View v){
            ClipData data = ClipData.newPlainText("","");
            int p = (int)((TextView)v).getTag();
            sound.play(wSounds[p],1,1,1,0,1);
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v,0);
            return false;
        }
    };

    View.OnClickListener onClickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.reboot:
                    reboot();
                    break;
                case R.id.ill:
                    sound.play(wordsound,1,1,1,0,0);
                    break;
            }
        }
    };
}
