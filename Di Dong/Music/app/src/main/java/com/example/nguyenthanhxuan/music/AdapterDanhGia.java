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

public class AdapterDanhGia extends ArrayAdapter<DanhGiaModel> {
    Context context;
    int resource;
    List<DanhGiaModel> objects;

    public AdapterDanhGia(@NonNull Context context, int resource, @NonNull List<DanhGiaModel> objects) {
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
        TextView tvUserDg=convertView.findViewById(R.id.tvUserDg);
        TextView tvValue=convertView.findViewById(R.id.tvValue);

        tvValue.setText(objects.get(position).getValue().toString());
        tvUserDg.setText(objects.get(position).getUserdg().toString());
        return convertView;
    }
}
