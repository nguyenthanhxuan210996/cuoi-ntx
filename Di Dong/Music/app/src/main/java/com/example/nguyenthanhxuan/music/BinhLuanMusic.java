package com.example.nguyenthanhxuan.music;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.nguyenthanhxuan.music.MainActivity.name;
import static com.example.nguyenthanhxuan.music.ListMusicLove.songs;
import static com.example.nguyenthanhxuan.music.ListMusicLove.idsongs;

/**
 * Created by Nguyen Thanh Xuan on 5/17/2018.
 */

public class BinhLuanMusic extends AppCompatActivity {
    private EditText edComment15;
    private TextView tvOwner14;
    private TextView tvTopicName14;
    private Button btnComment14;
    private ListView lv14;
    private ArrayAdapter<BinhLuan> commentAdapter;
    private List<BinhLuan> comments;
    private Button btnReadComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binhluan);

        edComment15=findViewById(R.id.edComment15);
        tvOwner14=findViewById(R.id.tvOwner14);
        tvTopicName14=findViewById(R.id.tvTopicName14);
        btnComment14=findViewById(R.id.btnComment14);
        lv14=findViewById(R.id.lv14);

        btnReadComment=findViewById(R.id.btnReadComment);

        for(Song playlist:songs){
            if(playlist.getIdbh().equals(idsongs)==true){
                tvOwner14.setText("UserName: "+playlist.getUser());
                tvTopicName14.setText("Bài Hát: "+ playlist.getSongName());
            }
        }

        btnComment14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edComment15.length()==0){
                    Toast.makeText(BinhLuanMusic.this,"Mời nhập comment",Toast.LENGTH_SHORT).show();
                }
                else {
                    String com15=edComment15.getText().toString();
                    String url="http://192.168.43.204/music/api/music?&danhgia="+com15+"&userdanhgia="+name;
                    new InsertComment().execute(url);

                }
            }
        });
        btnReadComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comments=new ArrayList<>();
                new ReadComments().execute("http://192.168.43.204/music/api/music?c=c");
            }
        });
    }

    private class ReadComments extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder stringBuilder=new StringBuilder(); //gan du lieu doc
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
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
            try{
                JSONArray array=new JSONArray(s) ;
                for(int i=0;i<array.length();i++){
                    JSONObject activity=array.getJSONObject(i);
                    int idcomment=activity.getInt("iddanhgia");
                    String com=activity.getString("danhgia");
                    String topicid=activity.getString("danhgiaid");
                    String usercomment=activity.getString("userdanhgia");
                    comments.add(new BinhLuan(idcomment,com,topicid,usercomment));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            commentAdapter=new AdapterBinhLuan(BinhLuanMusic.this,R.layout.activity_gdbinhluan,comments);
            lv14.setAdapter(commentAdapter);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

    private class InsertComment extends AsyncTask<String, Void, String> {
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
            Toast.makeText(BinhLuanMusic.this,"Đánh giá thành công",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }
}
