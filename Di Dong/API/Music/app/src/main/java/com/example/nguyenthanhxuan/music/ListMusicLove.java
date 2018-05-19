package com.example.nguyenthanhxuan.music;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

/**
 * Created by Nguyen Thanh Xuan on 5/17/2018.
 */

public class ListMusicLove extends AppCompatActivity {

    public static List<Song> songs;
    private ArrayAdapter<Song> songArrayAdapter;
    private Button btnInsertTopic12;
    private ListView lv12;
    public static String idsongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertmusiclove);

        songs=new ArrayList<>();
        btnInsertTopic12=findViewById(R.id.btnInsertTopic12);
        lv12=findViewById(R.id.lv12);
        new ReadTopics().execute("http://192.168.43.204/music/api/music"+"?t=t");
        lv12.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                idsongs=songs.get(i).getIdbh();
                Intent intent121=new Intent(ListMusicLove.this,DanhGiaBinhLuan.class);
                startActivity(intent121);
            }
        });
        btnInsertTopic12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent12=new Intent(ListMusicLove.this,InsertMusicLover.class);
                startActivity(intent12);
            }
        });

    }


    private class ReadTopics extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... strings) {

            StringBuilder stringBuilder=new StringBuilder(); //gan du lieu doc
            try {
                URL url=new URL(strings[0]);
                //URLConnection urlConnection=url.openConnection(); //mo connection
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                // lay du lieu tu url connection

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
            System.out.println(s);

            try{
                JSONArray array=new JSONArray(s) ;
                for(int i=0;i<array.length();i++){
                    JSONObject activity=array.getJSONObject(i);
                    String idtopic=activity.getString("idbh");
                    String owner=activity.getString("owner");
                    String songname=activity.getString("name");
                    String info=activity.getString("info");
                    songs.add(new Song(idtopic,owner,songname,info));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            songArrayAdapter=new MusicSongAdapter(ListMusicLove.this,R.layout.acitvity_listmusiclove,songs);
            lv12.setAdapter(songArrayAdapter);

        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

    }

}
