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
import android.widget.TextView;

public class Lesson5 extends Lesson {
    private int letterSound, letter2Sound;
    private String letter, letter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_ll);

        letter = getResources().getStringArray(R.array.alphabet)[position];
        letter2 = getResources().getStringArray(R.array.longletter)[position];

        TextView textView1 = (TextView) findViewById(R.id.shortLetter);
        textView1.setText(letter);
        textView1.setTextColor(Color.RED);
        textView1.setOnClickListener(onClickListener2);

        TextView textView2 = (TextView) findViewById(R.id.longLetter);
        textView2.setText(letter2);
        textView2.setTextColor(Color.RED);
        textView2.setOnClickListener(onClickListener2);

        findViewById(R.id.menu).setOnClickListener(onClickListener);
        findViewById(R.id.back).setOnClickListener(onClickListener);
        findViewById(R.id.forward).setOnClickListener(onClickListener);

        lessonSound = R.raw.lesson5;
        nClass = Lesson6.class;
        bClass = Lesson4.class;
    }

    public void onResume(){
        super.onResume();
        letterSound = sound.load(this,getValue.getStringSound(letter),1);
        letter2Sound = sound.load(this,getValue.getStringSound(letter2),1);
    }

    View.OnClickListener onClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.shortLetter:
                    sound.play(letterSound,1,1,1,0,1);
                    break;
                case R.id.longLetter:
                    sound.play(letter2Sound,1,1,1,0,1);
                    break;
            }
        }
    };
}
