package com.example.user.sahaabcv11;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class Lesson extends AppCompatActivity {
    protected SoundPool sound;
    protected MediaPlayer mediaPlayer;
    protected GetValue getValue;
    protected int position;
    protected int lessonSound;
    protected Class nClass;
    protected Class bClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getValue = new GetValue(this);
        position = getIntent().getExtras().getInt("position");

    }

    public void onPause() {
        super.onPause();
        sound.autoPause();
        sound.release();
        if (mediaPlayer!=null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    public void onResume(){
        super.onResume();
        mediaPlayer = MediaPlayer.create(this,lessonSound);
        mediaPlayer.setLooping(false);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

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
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.menu:
                    Intent intent = new Intent(getApplicationContext(), Abc_menu.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.back:
                    Intent intent2 = new Intent(getApplicationContext(), bClass);
                    intent2.putExtra("position",position);
                    startActivity(intent2);
                    finish();
                    break;
                case R.id.forward:
                    Intent intent3 = new Intent(getApplicationContext(), nClass);
                    intent3.putExtra("position", position);
                    startActivity(intent3);
                    finish();
                    break;
            }
        }
    };
}
