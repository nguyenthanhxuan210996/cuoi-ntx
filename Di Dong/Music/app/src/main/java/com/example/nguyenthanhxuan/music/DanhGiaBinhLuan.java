package com.example.nguyenthanhxuan.music;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

public class DanhGiaBinhLuan extends AppCompatActivity {
    private Button btnDanhGia;
    private Button btnBinhLuan;
    private Button btnXoa;
    public Song song;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binhluan_danhgia);

        btnDanhGia=(Button)findViewById(R.id.btnDanhGia);
        btnBinhLuan=(Button)findViewById(R.id.btnBinhLuan);
        btnXoa=(Button)findViewById(R.id.btnXoa);

        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent12=new Intent(DanhGiaBinhLuan.this,DanhGia.class);
                startActivity(intent12);
            }
        });

        btnBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent12=new Intent(DanhGiaBinhLuan.this,BinhLuanMusic.class);
                startActivity(intent12);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String url="http://192.168.43.204/music/api/music?&namedel="+name;
                    new Xoa().execute(url);
            }
        });
    }
    private class Xoa extends AsyncTask<String, Void, String> {
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
                httpURLConnection.setRequestMethod("DELETE");
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
            Toast.makeText(DanhGiaBinhLuan.this,"Xóa thành công",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }
}
