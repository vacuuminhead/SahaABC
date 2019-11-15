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

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Lesson10 extends Lesson {
    private int rightSound, wrongSound, sylSound;
    private int count;
    private TextView[] textView;
    private ProgressBar progressBar;
    private String[] sylls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rl_pb);

        count = 0;
        sylls = getValue.getSyllables(position);

        findViewById(R.id.menu).setOnClickListener(onClickListener);
        findViewById(R.id.back).setOnClickListener(onClickListener);
        if (position > 14) findViewById(R.id.forward).setOnClickListener(onClickListener);
        else findViewById(R.id.forward).setVisibility(View.INVISIBLE);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.ground);

        int size = getResources().getInteger(R.integer.lesson10_text_size);
        textView = new TextView[4];
        for (int i = 0; i < 4; i++) {
            textView[i] = new TextView(this);
            textView[i].setTextSize(size);
            textView[i].setTextColor(Color.BLACK);
            layout.addView(textView[i]);
        }

        progressBar = (ProgressBar) findViewById(R.id.prbar);
        progressBar.setMax(10);
        progressBar.setProgress(0);

        lessonSound = R.raw.lesson10;
        nClass = Lesson11.class;
        if (position > 41) bClass = Lesson1.class;
        else bClass = Lesson9.class;
    }

    public void onResume() {
        super.onResume();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                putSyllables();
            }
        });

        wrongSound = sound.load(this, R.raw.wrong, 1);
        rightSound = sound.load(this, R.raw.right, 1);
    }

    private void putSyllables() {
        Random random = new Random();
        Pol pol = new Pol(4, 4, 2);

        String right = sylls[random.nextInt(sylls.length)];
        sylSound = sound.load(getApplicationContext(), getValue.getStringSound(right), 1);
        sound.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                sound.play(sylSound, 1, 1, 1, 0, 1);
            }
        });

        Set<String> generator = new HashSet<>();
        while (generator.size() < 3) {
            String p = sylls[random.nextInt(sylls.length)];
            if (!p.equals(right))
                generator.add(p);
        }
        int x = getResources().getDimensionPixelOffset(R.dimen.lesson10_x);
        int y = getResources().getDimensionPixelOffset(R.dimen.lesson10_y);
        String[] syllables = generator.toArray(new String[generator.size()]);
        textView[0].setText(right);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = x * pol.x[0];
        params.topMargin = y * pol.y[0];
        textView[0].setLayoutParams(params);
        textView[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSyllables();
                count++;
                progressBar.setProgress(count);
                if (count == 10) {
                    sound.play(rightSound, 1, 1, 1, 0, 1);
                   /* if (position >14)
                    findViewById(R.id.forward).setVisibility(View.VISIBLE);*/
                }
            }
        });

        for (int i = 1; i < 4; i++) {
            textView[i].setText(syllables[i - 1]);
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params2.leftMargin = x * pol.x[i];
            params2.topMargin = y * pol.y[i];
            textView[i].setLayoutParams(params2);
            textView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sound.play(wrongSound, 1, 1, 1, 0, 1);
                    putSyllables();
                }
            });
        }
    }
}
