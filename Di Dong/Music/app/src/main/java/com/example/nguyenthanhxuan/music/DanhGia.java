package com.example.nguyenthanhxuan.music;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.nguyenthanhxuan.music.ListMusicLove.idsongs;
import static com.example.nguyenthanhxuan.music.ListMusicLove.songs;
import static com.example.nguyenthanhxuan.music.MainActivity.name;

/**
 * Created by Nguyen Thanh Xuan on 5/17/2018.
 */

public class DanhGia extends AppCompatActivity {

    private RatingBar rb;
    private TextView value;
    private TextView tvOwner15,tvTopicName15;
    private Button btnsend;
    public float values;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhgia);

        rb = (RatingBar) findViewById(R.id.ratingBar);
        value = (TextView) findViewById(R.id.value);
        tvOwner15 = (TextView) findViewById(R.id.tvOwner15);
        tvTopicName15 = (TextView) findViewById(R.id.tvTopicName15);
        btnsend = (Button) findViewById(R.id.btnsend);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                value.setText("Value is:" + rating);
                values = rating;
            }
        });
        for(Song playlist:songs){
            if(playlist.getIdbh().equals(idsongs)==true){
                tvOwner15.setText("UserName: "+playlist.getUser());
                tvTopicName15.setText("Bài Hát: "+ playlist.getSongName());
            }
        }

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String url="http://192.168.43.204/music/api/music?&value="+values+"&userdg="+name;
                    new InsertDanhGia().execute(url);
            }
        });
    }

    private class InsertDanhGia extends AsyncTask<String, Void, String> {
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
            Toast.makeText(DanhGia.this,"Đánh giá thành công",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }
}
