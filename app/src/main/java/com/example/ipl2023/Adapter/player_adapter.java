package com.example.ipl2023.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ipl2023.Activity.player;
import com.example.ipl2023.R;

public class player_adapter extends BaseAdapter {
    player player;
    int[] img;
    String[] play;
    public player_adapter(player player, int[] img, String[] play) {
        this.player=player;
        this.img=img;
        this.play=play;
    }


    @Override
    public int getCount() {
        return play.length;
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

        convertView = LayoutInflater.from(player).inflate(R.layout.player_item,parent,false);
        ImageView imageView = convertView.findViewById(R.id.player_img);
        TextView textView = convertView.findViewById(R.id.player_name);

        imageView.setImageResource(img[position]);
        textView.setText(play[position]);

        return convertView;
    }
}
