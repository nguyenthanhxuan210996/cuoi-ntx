package com.example.nguyenthanhxuan.music;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
/**
 * Created by Nguyen Thanh Xuan on 5/15/2018.
 */

public class Music extends AppCompatActivity {

    private ArrayList<SongInfo> _songs = new ArrayList<SongInfo>();
    RecyclerView recyclerView;
    SongAdapter songAdapter;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_baihat);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        _songs.add(new SongInfo("Tâm sự tuổi 30 ",
                "Trịnh Thăng Bình", "https://drive.google.com/uc?id=1gU9RRzNxasMVyxRG2686IkcvWV7VMxW7"));
        _songs.add(new SongInfo("Áo mới Cà Mau ",
                "Dương Hồng Loan", "https://drive.google.com/uc?id=1-9GP0w8gtpi-tgvxBf6W7fNXUtCoCkMn"));
        _songs.add(new SongInfo("Em ngày xưa khác rồi ",
                "Hiền Hồ", "https://drive.google.com/uc?id=1O3jPeP9cvrUo1MfiEvhgHsyX5mzeBuQJ"));
        _songs.add(new SongInfo("Mình cưới nhau đi ",
                "Huỳnh James", "https://drive.google.com/uc?id=192J3lz55P3giWRyqVKEPvPr-yE2PbjA1"));
        _songs.add(new SongInfo("Bùa Yêu ",
                "Bích Phương", "https://drive.google.com/uc?id=1mf_ncFZ32SoR_W26tyWD_qG-KAO1u8I8"));
        _songs.add(new SongInfo("Yêu Thương Ngày đó",
                "Soobin Hoàng Sơn", "https://drive.google.com/uc?id=1szOsU_JRuPRqWez2z2XVyXUfPJ6Sugp2"));
        _songs.add(new SongInfo("Rời bỏ ",
                "Hòa Minzy", "https://drive.google.com/uc?id=1KUYcHRoTaw5_7-e_fxs_Mm2ddAtoyBKM"));
        _songs.add(new SongInfo("Đừng như thói quen ",
                "Sara", "https://drive.google.com/uc?id=1SAkaCSx4jkklTr8n1r5BLAMBPOPBh5HU"));
        _songs.add(new SongInfo("Ai khóc nỗi đau này ",
                "Bảo Anh", "https://drive.google.com/uc?id=1zrobcrXqEaNfIrthnAE2TuW52MYgpg6R"));
        _songs.add(new SongInfo("Cô gái M52 ",
                "Huy", "https://drive.google.com/uc?id=1oLPkEbdVN7h1WBagLzQx4ivLiODeqLOK"));
        songAdapter = new SongAdapter(this, _songs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(songAdapter);

        songAdapter.setOnitemClickListener(new SongAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(final Button b, View v, final SongInfo obj, int position) {
                try {
                    if (b.getText().toString().equals("Stop")) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.release();
                        mediaPlayer = null;
                        b.setText("Play");
                    } else {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(obj.getSongUrl());
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.start();
                                b.setText("Stop");
                            }
                        });

                    }
                } catch (IOException e) {

                }

            }
        });
    }
}