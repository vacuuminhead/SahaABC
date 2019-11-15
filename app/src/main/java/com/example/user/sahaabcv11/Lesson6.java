package com.example.user.sahaabcv11;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class Lesson6 extends Lesson {
    private int rightSound, wrongSound, letterSound, letter2Sound, task1, task2;
    private String letter, letter2;
    private int rightS, wrongS, task;
    private int count, countL;
    private TextView[] textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rl_pb);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.ground);
        textView = new TextView[8];
        count = 0;

        findViewById(R.id.menu).setOnClickListener(onClickListener);
        findViewById(R.id.back).setOnClickListener(onClickListener);
        findViewById(R.id.forward).setVisibility(View.INVISIBLE);

        int size = getResources().getInteger(R.integer.lesson6_text_size);
        for (int i = 0; i < 8; i++) {
            textView[i] = new TextView(this);
            textView[i].setTextSize(size);
            textView[i].setTextColor(Color.RED);
            layout.addView(textView[i]);
        }

        progressBar = (ProgressBar) findViewById(R.id.prbar);
        progressBar.setProgress(0);
        progressBar.setMax(4);

        lessonSound = R.raw.lesson6;
        bClass = Lesson5.class;
    }

    public void onResume(){
        super.onResume();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                putLetters();
            }
        });
        letter = getResources().getStringArray(R.array.alphabet)[position];
        letter2 = getResources().getStringArray(R.array.longletter)[position];
        wrongSound = sound.load(this,R.raw.wrong,1);
        rightSound = sound.load(this,R.raw.right,1);
        letterSound = sound.load(this,getValue.getStringSound(letter),1);
        letter2Sound = sound.load(this,getValue.getStringSound(letter2),1);
        task1 = sound.load(this,R.raw.lesson6_short,1);
        task2 = sound.load(this,R.raw.lesson6_long,1);
    }

    private void putLetters() {
        Random random = new Random();
        Pol pol = new Pol(8,2,2);
        String right,wrong;
        countL = 4;

        if (random.nextBoolean()) {
            right = letter;
            wrong = letter2;
            rightS = letterSound;
            wrongS = letter2Sound;
            task = task1;

        } else {
            right = letter2;
            wrong = letter;
            rightS = letter2Sound;
            wrongS = letterSound;
            task = task2;
        }

        float x = getResources().getDimension(R.dimen.lesson6_x);
        float y = getResources().getDimension(R.dimen.lesson6_y);
        for (int i = 0; i < 4; i++) {
            textView[i].setText(right);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = (int)(x * pol.x[i]);
            params.topMargin =(int)(y * pol.y[i]);
            textView[i].setLayoutParams(params);
            textView[i].setOnClickListener(onClickListener2);
            textView[i].setVisibility(View.VISIBLE);
        }
        for (int i = 4; i < 8; i++) {
            textView[i].setText(wrong);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = (int)(x * pol.x[i]);
            params.topMargin =(int)(y * pol.y[i]);
            textView[i].setLayoutParams(params);
            textView[i].setOnClickListener(onClickListener3);
        }

        sound.play(task,1,1,1,0,1);
    }
    View.OnClickListener onClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sound.play(rightS,1,1,1,0,1);
            v.setVisibility(View.INVISIBLE);
            countL--;
            if(countL==0){
                sound.play(rightSound,1,1,1,0,1);
                count++;
                progressBar.setProgress(count);
                putLetters();
            }
        }
    };
    View.OnClickListener onClickListener3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sound.play(wrongSound,1,1,1,0,1);
            putLetters();
        }
    };
}
