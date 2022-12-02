package com.example.ipl2023.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ipl2023.Adapter.player_adapter;
import com.example.ipl2023.R;
import com.example.ipl2023.config;

public class player extends AppCompatActivity {

    ListView listView;
    int team;
    String[] play;
    int[] img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_layout);

        listView = findViewById(R.id.playerlist);

        team = getIntent().getIntExtra("team",0);

        if(team==0)
        {
            img = config.csk_img;
            play = config.csk_name;
        }
        if(team==1)
        {
            img = config.dc_img;
            play = config.dc_name;
        }
        if(team==2)
        {
            img = config.kkr_img;
            play = config.kkr_name;
        }
        if(team==3)
        {
            img = config.mi_img;
            play = config.mi_name;
        }
        if(team==4)
        {
            img = config.rcb_img;
            play = config.rcb_name;
        }
        if(team==5)
        {
            img = config.rr_img;
            play = config.rr_name;
        }
        if(team==6)
        {
            img = config.srh_img;
            play = config.srh_name;
        }

        player_adapter player_adapter = new player_adapter(player.this,img,play);
        listView.setAdapter(player_adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(player.this,view_player.class);
            intent.putExtra("team",position);
            intent.putExtra("img",img);
            intent.putExtra("play",play);
            startActivity(intent);
        });

    }
}