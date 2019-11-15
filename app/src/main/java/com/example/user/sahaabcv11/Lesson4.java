package com.example.user.sahaabcv11;

import android.content.Intent;
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

public class Lesson4 extends Lesson{
    private MediaPlayer mediaPlayer1;
    private String right;
    private int letterSound, rightSound, wrongSound;
    private int count;
    private TextView[] textView;
    private ProgressBar progressBar;
    private String[] wrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rl_pb);
        count=0;
        if (position>22&&position<27)
            wrong=getResources().getStringArray(R.array.dif_2);
        else
            wrong=getResources().getStringArray(R.array.alphabet_2);

        findViewById(R.id.menu).setOnClickListener(onClickListener);
        findViewById(R.id.back).setOnClickListener(onClickListener);
        findViewById(R.id.forward).setOnClickListener(onClickListener);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.ground);
        textView=new TextView[9];
        int size = getResources().getInteger(R.integer.lesson4_text_size);
        for(int i=0;i<9;i++) {
            textView[i] = new TextView(this);
            textView[i].setTextSize(size);
            layout.addView(textView[i]);
        }

        progressBar = (ProgressBar)findViewById(R.id.prbar);
        progressBar.setMax(10);
        progressBar.setProgress(0);
        right = getResources().getStringArray(R.array.alphabet)[position];

        lessonSound = getValue.getStringSound(right);
        bClass = Lesson2.class;
        if (position<8) nClass = Lesson5.class;
        else if((position>41)) nClass = Lesson8.class;
        else nClass = Lesson7.class;
    }

    public void onResume(){
        super.onResume();
        if (position>22 && position<27)
            mediaPlayer1 = MediaPlayer.create(getApplicationContext(),R.raw.lesson4_dif);
        else
            mediaPlayer1 = MediaPlayer.create(getApplicationContext(),R.raw.lesson4);
        mediaPlayer1.setLooping(false);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer1.start();
            }
        });

        wrongSound = sound.load(this,R.raw.wrong,1);
        rightSound = sound.load(this,R.raw.right,1);
        letterSound = sound.load(this,getValue.getStringSound(right),1);
        putLetters();
    }

    public void onPause(){
        super.onPause();
        if (mediaPlayer1!=null) {
            mediaPlayer1.stop();
            mediaPlayer1.release();
        }
    }
    private void putLetters(){
        Random random = new Random();
        Pol pol = new Pol(9,2,2);
        Set<String> generator = new HashSet<>();
        while (generator.size()<8){
            String p = wrong[random.nextInt(wrong.length)];
            if (!p.equals(right))
                generator.add(p);
        }
        String[] letters = generator.toArray(new String[generator.size()]);
        float x = getResources().getDimension(R.dimen.lesson2_x);
        float y = getResources().getDimension(R.dimen.lesson2_y);

        textView[0].setText(right);
        textView[0].setTextColor(getValue.getColor(right));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = (int)(x * pol.x[0]);
        params.topMargin = (int)(y * pol.y[0]);
        textView[0].setLayoutParams(params);
        textView[0].setOnClickListener(onClickListener1);

        for (int i=1;i<9;i++) {
            textView[i].setText(letters[i-1]);
            textView[i].setTextColor(getValue.getColor(letters[i-1]));
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
            params2.leftMargin = (int)(x * pol.x[i]);
            params2.topMargin = (int)(y * pol.y[i]);
            textView[i].setLayoutParams(params2);
            textView[i].setOnClickListener(onClickListener2);
        }
    }

    View.OnClickListener onClickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sound.play(letterSound,1,1,1,0,1);
            putLetters();
            count++;
            progressBar.setProgress(count);

            if (count==10) {
                sound.play(rightSound,1,1,1,0,1);
                ImageButton imageButton = (ImageButton) findViewById(R.id.forward);
                imageButton.setVisibility(View.VISIBLE);
            }
        }
    };

    View.OnClickListener onClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sound.play(wrongSound,1,1,1,0,1);
            putLetters();
        }
    };
}
