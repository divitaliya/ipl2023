package com.example.ipl2023.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ipl2023.Activity.team;
import com.example.ipl2023.R;
import com.example.ipl2023.config;

public class team_adapter extends BaseAdapter {
    team team;
    public team_adapter(team team) {
        this.team=team;
    }

    @Override
    public int getCount() {
        return config.team_img.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(team).inflate(R.layout.team_item,parent,false);
        ImageView imageView = convertView.findViewById(R.id.team_img);
        TextView textView = convertView.findViewById(R.id.team_name);

        imageView.setImageResource(config.team_img[position]);
        textView.setText(config.team_name[position]);

        return convertView;
    }
}
