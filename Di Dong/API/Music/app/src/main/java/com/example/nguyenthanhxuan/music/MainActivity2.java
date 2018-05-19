package com.example.nguyenthanhxuan.music;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Nguyen Thanh Xuan on 5/15/2018.
 */

public class MainActivity2 extends AppCompatActivity {
    private Button btnPlayMusic;
    private Button btnDoiPass;
    private Button btnListMusic;
    private Button btnDangXuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnPlayMusic = (Button) findViewById(R.id.btnPlayMusic);
        btnDoiPass = (Button) findViewById(R.id.btnDoiPass);
        btnListMusic=(Button)findViewById(R.id.btnListMusic);
        btnDangXuat=(Button)findViewById(R.id.btnDangXuat);

        btnPlayMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, Music.class);
                startActivity(intent);
            }
        });
        btnDoiPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, DoiPass.class);
                startActivity(intent);
            }
        });
        btnListMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, ListMusicLove.class);
                startActivity(intent);
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
