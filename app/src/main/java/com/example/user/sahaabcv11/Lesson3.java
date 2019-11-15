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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Lesson3 extends Lesson {
    protected String[] values;
    protected int[] valuesSound;
    protected int good;
    protected int size;
    protected static final int REST = 0;
    protected static final int CHOSEN= 1;
    protected static final int CLOSE = -1;
    protected TextView[] textViews;
    protected enum Status {REST, CHOSEN, CLOSE}
    protected ArrayList<Status> statusArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        findViewById(R.id.menu).setOnClickListener(onClickListener);
        findViewById(R.id.back).setOnClickListener(onClickListener);
        findViewById(R.id.forward).setOnClickListener(onClickListener);

        putValues();
        statusArrayList = new ArrayList<>();
        valuesSound = new int[12];

        textViews = new TextView[12];
        textViews[0] = (TextView)findViewById(R.id.tw0);
        textViews[1] = (TextView)findViewById(R.id.tw1);
        textViews[2] = (TextView)findViewById(R.id.tw2);
        textViews[3] = (TextView)findViewById(R.id.tw3);
        textViews[4] = (TextView)findViewById(R.id.tw4);
        textViews[5] = (TextView)findViewById(R.id.tw5);
        textViews[6] = (TextView)findViewById(R.id.tw6);
        textViews[7] = (TextView)findViewById(R.id.tw7);
        textViews[8] = (TextView)findViewById(R.id.tw8);
        textViews[9] = (TextView)findViewById(R.id.tw9);
        textViews[10] = (TextView)findViewById(R.id.tw10);
        textViews[11] = (TextView)findViewById(R.id.tw11);

        for (int i = 0; i<12; i++){
            textViews[i].setText(values[i]);
            textViews[i].setTextSize(size);
            textViews[i].setTextColor(Color.BLACK);
            textViews[i].setBackgroundColor(Color.parseColor("#FFFFBD"));
            textViews[i].setOnClickListener(onClickListener1);
            textViews[i].setTag(i);
            statusArrayList.add(Status.REST);
        }

        lessonSound = R.raw.lesson3;
        nClass = Lesson4.class;
        bClass = Lesson2.class;
    }

    protected  void putValues(){
        String[] letters = new String[position+1];
        System.arraycopy(getResources().getStringArray(R.array.alphabet),0,letters,0,position+1);
        ArrayList<String> arrayList = new ArrayList<>();
        Random random = new Random();
        while (arrayList.size() < 12){
            String p = letters[random.nextInt(letters.length)];
            if (!arrayList.contains(p)){
                arrayList.add(p);
                arrayList.add(p);
            }
        }
        Collections.shuffle(arrayList);
        values = arrayList.toArray(new String[arrayList.size()]);
        size = getResources().getInteger(R.integer.TA_textsize);
    }

    public void onResume(){
        super.onResume();
        for (int i = 0; i<12; i++) valuesSound[i] = sound.load(getApplicationContext(), getValue.getStringSound(values[i]),1);
        good = sound.load(this, R.raw.right, 1);
    }

    View.OnClickListener onClickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int p = (int)((TextView)v).getTag();
            if (!statusArrayList.get(p).equals(Status.CLOSE)) statusArrayList.set(p,Status.CHOSEN);
            sound.play(valuesSound[p],1,1,0,0,1);

            int first = statusArrayList.indexOf(Status.CHOSEN);
            int second = statusArrayList.lastIndexOf(Status.CHOSEN);

            if (first == second)
                textViews[first].setBackgroundColor(Color.GREEN);
            else if(values[first].equals(values[second])){
                statusArrayList.set(first, Status.CLOSE);
                textViews[first].setVisibility(View.INVISIBLE);
                statusArrayList.set(second, Status.CLOSE);
                textViews[second].setVisibility(View.INVISIBLE);
            }
            else {
                statusArrayList.set(first, Status.REST);
                textViews[first].setBackgroundColor(Color.parseColor("#FFFFBD"));
                statusArrayList.set(second, Status.REST);
                textViews[second].setBackgroundColor(Color.parseColor("#FFFFBD"));
            }
            if (statusArrayList.indexOf(Status.REST)<0){
                sound.play(good,1,1,2,0,1);
            }
        }
    };
}
