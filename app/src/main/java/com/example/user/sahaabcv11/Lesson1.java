package com.example.user.sahaabcv11;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Lesson1 extends Lesson {
    private MediaPlayer  mediaPlayer1;
    private String let, word;
    private int letterSound, imageSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_i_w);

        TextView textView = (TextView) findViewById(R.id.letter);
        let = getResources().getStringArray(R.array.alphabet)[position];
        textView.setText(let);
        int col = getValue.getColor(let);
        textView.setTextColor(col);
        textView.setOnClickListener(onClickListener2);

        ImageView imageView = (ImageView) findViewById(R.id.illustration);
        imageView.setImageResource(pics[position]);
        imageView.setOnClickListener(onClickListener2);

        TextView textView1 = (TextView) findViewById(R.id.firstword);
        word = words[position];
        Spannable sb = new SpannableString(word);
        sb.setSpan(new ForegroundColorSpan(Color.BLACK),0,word.length(),0);
        sb.setSpan(new ForegroundColorSpan(col),word.indexOf(let), word.indexOf(let) + let.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView1.setText(sb);
        textView1.setVisibility(View.INVISIBLE);

        findViewById(R.id.menu).setOnClickListener(onClickListener);
        findViewById(R.id.forward).setOnClickListener(onClickListener);

        lessonSound = lessons[position];
        if (position>41) nClass = Lesson7.class;
        else nClass = Lesson2.class;
    }

    public void onResume(){
        super.onResume();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (position>40)
                    mediaPlayer1 = MediaPlayer.create(getApplicationContext(),R.raw.lesson1_znak);
                else
                    mediaPlayer1 = MediaPlayer.create(getApplicationContext(),R.raw.lesson1);
                mediaPlayer1.setLooping(false);
                mediaPlayer1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
            }
        });

        if (position<42)
        letterSound = sound.load(this,getValue.getStringSound(let),1);
        imageSound = sound.load(this,getValue.getStringSound(word),1);

    }

    public void onPause() {
        super.onPause();
        if (mediaPlayer1!=null) {
            mediaPlayer1.stop();
            mediaPlayer1.release();
        }
    }

    View.OnClickListener onClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.letter:
                    if (position < 42)
                        sound.play(letterSound, 1, 1, 1, 0, 1);
                    break;
                case R.id.illustration:
                    sound.play(imageSound, 1, 1, 1, 0, 1);
                    findViewById(R.id.firstword).setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    private int[] pics = {
            R.drawable.il_at, R.drawable.il_uja, R.drawable.il_ilii, R.drawable.il_eit, R.drawable.il_ot,
            R.drawable.il_eotyje, R.drawable.il_ehe, R.drawable.il_ytylyk, R.drawable.il_sibekki, R.drawable.il_mas,
            R.drawable.il_rak, R.drawable.il_leiax, R.drawable.il_bagha, R.drawable.il_tiign, R.drawable.il_kus,
            R.drawable.il_xajeihar, R.drawable.il_nosorog, R.drawable.il_jogurt, R.drawable.il_pingvin, R.drawable.il_globus,
            R.drawable.il_chaaskei, R.drawable.il_bagha, R.drawable.il_ehe, R.drawable.il_leiax, R.drawable.il_njuoska,
            R.drawable.il_djie, R.drawable.il_deolyhyeon, R.drawable.il_deolyhyeon, R.drawable.il_djie, R.drawable.il_tiign,
            R.drawable.il_njuoska, R.drawable.il_jabloko, R.drawable.il_jula, R.drawable.il_jenot, R.drawable.il_jozh,
            R.drawable.il_sharik, R.drawable.il_vaza, R.drawable.il_zjebra, R.drawable.il_zhiraf,  R.drawable.il_fonarik,
            R.drawable.il_tsirk, R.drawable.il_sthit, R.drawable.il_fonarik
    };

    private String[] words ={
                "АТ","УЙА","ИЛИИ","ЫТ","ОТ",
                "ӨТҮЙЭ","ЭҺЭ","ҮТҮЛҮК","СИБЭККИ","МАС",
                "РАК","ЛЫАХ","БАҔА","ТИИҤ","КУС",
                "ХАЙЫҺАР","НОСОРОГ","ЙОГУРТ","ПИНГВИН","ГЛОБУС",
                "ЧААСКЫ","БАҔА","ЭҺЭ","ЛЫАХ","НьУОСКА",
                "ДьИЭ","ДӨЛҮҺҮӨН","ДӨЛҮҺҮӨН","ДьИЭ","ТИИҤ",
                "НьУОСКА","ЯБЛОКО","ЮЛА","ЕНОТ","ЁЖ",
                "ШАРИК","ВАЗА","ЗЕБРА","ЖИРАФ","ФОНАРИК",
                "ЦИРК","ЩИТ","ФОНАРЬ"
    };
    private int[] lessons = {
            R.raw.lesson1_a, R.raw.lesson1_u, R.raw.lesson1_i, R.raw.lesson1_ei, R.raw.lesson1_o,
            R.raw.lesson1_eo, R.raw.lesson1_e, R.raw.lesson1_y, R.raw.lesson1_s, R.raw.lesson1_m,
            R.raw.lesson1_r, R.raw.lesson1_l, R.raw.lesson1_b, R.raw.lesson1_t, R.raw.lesson1_k,
            R.raw.lesson1_x, R.raw.lesson1_n, R.raw.lesson1_j, R.raw.lesson1_p, R.raw.lesson1_g,
            R.raw.lesson1_ch, R.raw.lesson1_gh, R.raw.lesson1_h, R.raw.lesson1_eia, R.raw.lesson1_uo,
            R.raw.lesson1_ie, R.raw.lesson1_yeo, R.raw.lesson1_d, R.raw.lesson1_dj, R.raw.lesson1_gn,
            R.raw.lesson1_nj, R.raw.lesson1_ja, R.raw.lesson1_ju, R.raw.lesson1_je, R.raw.lesson1_jo,
            R.raw.lesson1_sh, R.raw.lesson1_v, R.raw.lesson1_z, R.raw.lesson1_zh, R.raw.lesson1_f,
            R.raw.lesson1_ts, R.raw.lesson1_sth, R.raw.lesson1_znak1, R.raw.lesson1_znak2
    };
}