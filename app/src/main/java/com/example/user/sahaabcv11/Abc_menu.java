package com.example.user.sahaabcv11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class Abc_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_menu);

        GridView gridview = (GridView) findViewById(R.id.abc);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (position==0){
                    Intent intent = new Intent(Abc_menu.this, Lesson0.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(Abc_menu.this, Lesson1.class);
                    intent.putExtra("position", position-1);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
