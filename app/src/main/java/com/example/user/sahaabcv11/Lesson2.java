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

public class Lesson2 extends Lesson {
    private String letter;
    private int letterSound, good;
    private int count;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rl_pb);

        count=0;
        findViewById(R.id.menu).setOnClickListener(onClickListener);
        findViewById(R.id.back).setOnClickListener(onClickListener);
        findViewById(R.id.forward).setOnClickListener(onClickListener);

        progressBar= (ProgressBar)findViewById(R.id.prbar);
        progressBar.setProgress(0);
        progressBar.setMax(9);

        letter = getResources().getStringArray(R.array.alphabet)[position];

        Pol pol = new Pol(9,2,2);
        float x = getResources().getDimensionPixelSize(R.dimen.lesson2_x);
        float y = getResources().getDimensionPixelSize(R.dimen.lesson2_y);
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.ground);
        TextView[] textView=new TextView[9];
        int size = getResources().getInteger(R.integer.lesson6_text_size);
        for(int i=0;i<9;i++) {
            textView[i] = new TextView(getApplicationContext());
            textView[i].setText(letter);
            textView[i].setTextSize(size);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = (int)(x * pol.x[i]);
            layoutParams.topMargin = (int)(y * pol.y[i]);
            textView[i].setLayoutParams(layoutParams);
            textView[i].setTextColor(colors[i]);
            textView[i].setVisibility(View.VISIBLE);
            textView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sound.play(letterSound,1,1,1,0,1);
                    v.setVisibility(View.INVISIBLE);
                    count++;
                    progressBar.setProgress(count);
                    if (count==9) {
                        sound.play(good,1,1,2,0,1);
                       /* ImageButton imageButton = (ImageButton) findViewById(R.id.forward);
                        imageButton.setVisibility(View.VISIBLE);*/
                    }
                }
            });
            layout.addView(textView[i]);
            if ((position>5 && position<23) || position>26) nClass = Lesson3.class;
            else nClass = Lesson4.class;
            bClass = Lesson1.class;

            if (position>22 && position<27) lessonSound = R.raw.lesson2_dif;
            else lessonSound = R.raw.lesson2;
        }
    }

    public void onResume(){
        super.onResume();
        letterSound = sound.load(this,getValue.getStringSound(letter),1);
        good = sound.load(this,R.raw.right,1);
    }

    private int[] colors = {Color.parseColor("#BA45BA"), Color.parseColor("#1B35F8"),
            Color.parseColor("#F8EE1B"), Color.parseColor("#FF2B14"),
            Color.parseColor("#FF8214"), Color.parseColor("#40E10E"),
            Color.parseColor("#0EE1C5"), Color.parseColor("#4D1F84"),
            Color.parseColor("#EB14F0")};
}
