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

public class AdapterBinhLuan extends ArrayAdapter<BinhLuan> {
    Context context;
    int resource;
    List<BinhLuan> objects;

    public AdapterBinhLuan(@NonNull Context context, int resource, @NonNull List<BinhLuan> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(resource,null);
        }
        TextView tvUserDanhGia=convertView.findViewById(R.id.tvUserDanhGia);
        TextView tvDanhGia=convertView.findViewById(R.id.tvDanhGia);

        String a=objects.get(position).getDanhgia().toString();

        tvDanhGia.setText(a);
        tvUserDanhGia.setText(objects.get(position).getUserdanhgia().toString());
        return convertView;
    }
}
