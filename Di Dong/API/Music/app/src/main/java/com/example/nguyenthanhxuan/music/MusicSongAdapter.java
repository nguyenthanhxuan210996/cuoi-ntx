package com.example.nguyenthanhxuan.music;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nguyen Thanh Xuan on 5/17/2018.
 */

public class MusicSongAdapter extends ArrayAdapter<Song> {

    Context context;
    int resource;
    List<Song> songs;



    public MusicSongAdapter(@NonNull Context context, int resource, List<Song> songs) {
        super(context, resource,songs);
        this.context=context;
        this.resource=resource;
        this.songs=songs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(resource,null);
        }

        TextView tvTopicName=convertView.findViewById(R.id.tvsongName);
        TextView tvOwner=convertView.findViewById(R.id.tvsong);
        TextView tvCasi=convertView.findViewById(R.id.tvCasi);

        Song s=songs.get(position);
        String a=s.getContent().toString().replace("-"," ");
        String b=s.getSongName().toString().replace("-"," ");
        tvTopicName.setText(b);
        tvCasi.setText(a);
        tvOwner.setText("username: "+s.getUser());
        return convertView;
    }
}

