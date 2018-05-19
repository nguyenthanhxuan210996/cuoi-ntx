package com.example.nguyenthanhxuan.music;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.nguyenthanhxuan.music.MainActivity.name;

/**
 * Created by Nguyen Thanh Xuan on 5/17/2018.
 */

public class InsertMusicLover extends AppCompatActivity {
    public EditText edContent14;
    private EditText edContent16;
    private Button btnInsert13;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gdinsertmusiclove);

        edContent14=findViewById(R.id.edContent14);
        edContent16=findViewById(R.id.edContent16);
        btnInsert13=findViewById(R.id.btnInsert13);

        btnInsert13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edContent14.length()==0||edContent16.length()==0){
                    Toast.makeText(InsertMusicLover.this,"Không được để trống",Toast.LENGTH_SHORT).show();
//                    if(edContent14.getText().toString()!="tamsutuoi30"&&edContent16.getText().toString()!="trinhthangbinh"
//                            ||edContent14.getText().toString()!="aomoicamau"&&edContent16.getText().toString()!="duonghongloan"
//                            ||edContent14.getText().toString()!="emngayxuakhacroi"&&edContent16.getText().toString()!="hienho"
//                            ||edContent14.getText().toString()!="minhcuoinhaudi"&&edContent16.getText().toString()!="huynhjames"
//                            ||edContent14.getText().toString()!="buayeu"&&edContent16.getText().toString()!="bichphuong"
//                            ||edContent14.getText().toString()!="yeuthuongngaydo"&&edContent16.getText().toString()!="soobinhoangson"
//                            ||edContent14.getText().toString()!="roibo"&&edContent16.getText().toString()!="hoaminzy"
//                            ||edContent14.getText().toString()!="dungnhuthoiquen"&&edContent16.getText().toString()!="sara"
//                            ||edContent14.getText().toString()!="aikhocnoidaunay"&&edContent16.getText().toString()!="baoanh"
//                            ||edContent14.getText().toString()!="cogaim52"&&edContent16.getText().toString()!="huy"){
//                        Toast.makeText(InsertMusicLover.this," Bài hát không tồn tại",Toast.LENGTH_SHORT).show();
//                    }

                }
                else {

                    String edit14 = edContent14.getText().toString();
                    String edit16 = edContent16.getText().toString();
                    String url = "http://192.168.43.204/music/api/music?&songname=" + edit16 + "&owner=" + name + "&info=" + edit14;
                    new Insertsong().execute(url);
                }
            }
        });
    }

    private class Insertsong extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder=new StringBuilder();
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line + "\n");
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String a = s.charAt(0) + "";
            Toast.makeText(InsertMusicLover.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

}

