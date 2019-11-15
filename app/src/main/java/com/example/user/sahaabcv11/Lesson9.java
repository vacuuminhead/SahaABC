package com.example.user.sahaabcv11;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Lesson9 extends Lesson3{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lessonSound = R.raw.lesson9;
        if (position == 31) bClass = Lesson7.class;
        else bClass = Lesson8.class;
        nClass = Lesson10.class;
    }

    protected void putValues(){
        String[] syllables = getValue.getSyllables(position);
        ArrayList<String> arrayList = new ArrayList<>();
        Random random = new Random();
        while (arrayList.size() < 12){
            String p = syllables[random.nextInt(syllables.length)];
            if (!arrayList.contains(p)){
                arrayList.add(p);
                arrayList.add(p);
            }
        }
        Collections.shuffle(arrayList);
        values = arrayList.toArray(new String[arrayList.size()]);
        size = getResources().getInteger(R.integer.TA_textsize2);
    }
}
