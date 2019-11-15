package com.example.user.sahaabcv11;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class Lesson0 extends AppCompatActivity {
    private SoundPool sound;
    private GetValue getValue;
    private int wordSound;
    private int textSound;
    private int size;
    private String[] word;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_il_w);

        ImageButton imageButton1 = (ImageButton)findViewById(R.id.menu);
        imageButton1.setOnClickListener(onClickListener);

        ImageButton imageButton2 = (ImageButton)findViewById(R.id.reboot);
        imageButton2.setOnClickListener(onClickListener);

        size = getResources().getInteger(R.integer.lesson10_text_size);
        getValue = new GetValue(this);
    }

    public void onResume(){
        super.onResume();

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            sound = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        } else {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            sound = new SoundPool.Builder()
                    .setAudioAttributes(attributes)
                    .build();
        }

        reboot();
    }
    private void reboot(){

        Random random = new Random();
        int pos = random.nextInt(14);
        word = words[pos];
        ImageView imageView = (ImageView)findViewById(R.id.illustration);
        imageView.setImageResource(ill[pos]);
        imageView.setOnClickListener(onClickListener);
        if (wordSound!=0)
            sound.unload(wordSound);
        wordSound = sound.load(this, getValue.getStringSound(word[0]),1);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.les0);
        linearLayout.removeAllViewsInLayout();

        TextView textView = new TextView(this);
        textView.setText(word[0]);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        textView.setLayoutParams(params);
        textView.setTextSize(size);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        textView.setOnClickListener(onClickListener2);
        linearLayout.addView(textView);
    }

    public void onPause() {
        super.onPause();
        sound.autoPause();
        sound.release();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.menu:
                    Intent intent = new Intent(getApplicationContext(), Abc_menu.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.reboot:
                    sound.unload(textSound);
                    reboot();
                    break;
                case R.id.illustration:
                    sound.play(wordSound,1,1,0,0,1);
                    break;
            }

        }
    };

    private View.OnClickListener onClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sound.play(wordSound,1,1,0,0,1);



            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.les0);
            linearLayout.removeAllViewsInLayout();

            for (int i=1;i<word.length;i++) {
                TextView textView = new TextView(getApplicationContext());
                textView.setText(word[i]);
                textView.setTextColor(getValue.getColor(word[i]));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(10, 0, 10, 0);
                textView.setLayoutParams(params);
                textView.setTextSize(size);
                textView.setGravity(Gravity.CENTER);
                textView.setOnClickListener(onClickListener1);
                linearLayout.addView(textView);
            }
        }
    };
    private View.OnClickListener onClickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            textSound = sound.load(getApplicationContext(),getValue.getStringSound(((TextView) v).getText().toString()),1);
            sound.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    sound.play(textSound,1,1,1,0,1);
                }
            });
        }
    };
    private String[][] words = {
            {"АТ","А","Т"},
            {"УЙА","У","Й","А"},
            {"ИЛИИ", "И", "Л", "ИИ"},
            {"ЫТ", "Ы", "Т"},
            {"ОТ", "О", "Т"},
            {"ӨТҮЙЭ", "Ө", "Т", "Ү", "Й", "Э"},
            {"ЭҺЭ", "Э", "Һ", "Э"},
            {"МАС", "М", "А", "С"},
            {"ЛЫАХ", "Л", "ЫА", "Х"},
            {"БАҔА", "Б", "А", "Ҕ", "А"},
            {"ТИИҤ", "Т", "ИИ", "Ҥ"},
            {"КУС", "К", "У" ,"С"},
            {"ДьИЭ", "Дь", "ИЭ"},
            {"ЧААСКЫ", "Ч", "АА", "С", "К", "Ы"}
    };
    private int[] ill = {
            R.drawable.il_at, R.drawable.il_uja, R.drawable.il_ilii, R.drawable.il_eit, R.drawable.il_ot,
            R.drawable.il_eotyje, R.drawable.il_ehe, R.drawable.il_mas, R.drawable.il_leiax, R.drawable.il_bagha,
            R.drawable.il_tiign, R.drawable.il_kus, R.drawable.il_djie, R.drawable.il_chaaskei
    };
}

