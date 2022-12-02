package com.example.ipl2023.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ipl2023.Adapter.team_adapter;
import com.example.ipl2023.R;
import com.example.ipl2023.config;

import java.io.File;

public class team extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_layout);

        ActivityCompat.requestPermissions(team.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);

        listView = findViewById(R.id.teamlist);

        team_adapter team_adapter = new team_adapter(this);
        listView.setAdapter(team_adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(team.this,player.class);
            intent.putExtra("team",position);
            startActivity(intent);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {


                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    config.file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/ipl");
                    if(config.file.exists())
                    {
                        System.out.println("folder is avilable");
                    }
                    else
                    {
                        System.out.println("folder is not avilable");
                        if(config.file.mkdir())
                        {
                            System.out.println("create folder");
                        }
                        else{
                            System.out.println("not create folder");
                        }
                    }
                } else {
                    ActivityCompat.requestPermissions(team.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                    Toast.makeText(team.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}