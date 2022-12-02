package com.example.ipl2023.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.palette.graphics.Palette;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipl2023.R;
import com.example.ipl2023.config;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class view_player extends AppCompatActivity {

    int team;
    String[] play;
    int[] img;
    ImageView imageView;
    TextView textView;
    Button prev, next, wallpepar, share, download;
    Bitmap bitmap;
    LinearLayout linearLayout;
    Palette p;
    Palette.Swatch vibrantSwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_player_layout);

        imageView = findViewById(R.id.view_img);
        textView = findViewById(R.id.view_name);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        linearLayout = findViewById(R.id.linear);
        wallpepar = findViewById(R.id.wallpepar);
        share = findViewById(R.id.share);
        download = findViewById(R.id.download);
        team = getIntent().getIntExtra("team", 0);
        play = getIntent().getStringArrayExtra("play");
        img = getIntent().getIntArrayExtra("img");

        imageView.setImageResource(img[team]);
        textView.setText(play[team]);

        bitmap = BitmapFactory.decodeResource(getResources(), img[team]);
        p = createPaletteSync(bitmap);

        vibrantSwatch = p.getVibrantSwatch();
        if (vibrantSwatch != null) {
            linearLayout.setBackgroundColor(vibrantSwatch.getRgb());
        }
        prev.setOnClickListener(v -> {
            team--;
            imageView.setImageResource(img[team]);
            textView.setText(play[team]);
            bitmap = BitmapFactory.decodeResource(getResources(), img[team]);
            p = createPaletteSync(bitmap);

            vibrantSwatch = p.getVibrantSwatch();
            if (vibrantSwatch != null) {
                linearLayout.setBackgroundColor(vibrantSwatch.getRgb());
            }
        });
        next.setOnClickListener(v -> {
            team++;
            imageView.setImageResource(img[team]);
            textView.setText(play[team]);
            bitmap = BitmapFactory.decodeResource(getResources(), img[team]);
            p = createPaletteSync(bitmap);

            vibrantSwatch = p.getVibrantSwatch();
            if (vibrantSwatch != null) {
                linearLayout.setBackgroundColor(vibrantSwatch.getRgb());
            }
        });
        String[] arr = {"lock screen", "home screen"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("wallpepar");
        builder.setItems(arr, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    if (which == 0) {
                        myWallpaperManager.setResource(img[team], WallpaperManager.FLAG_LOCK);
                    }
                    if (which == 1) {
                        myWallpaperManager.setResource(img[team], WallpaperManager.FLAG_SYSTEM);
                    }
                } catch (IOException e) {
                    //TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        download.setOnClickListener(v -> {

            Bitmap icon = getBitmapFromView(linearLayout);
            System.out.println("bitmap==" + icon);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            int randam = new Random().nextInt(2000);
            File f = new File(config.file.getAbsolutePath() + "/ipl" + "img_file" + randam + ".jpg");
            try {
                f.createNewFile();
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(bytes.toByteArray());
                Toast.makeText(this, "downlode file", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        share.setOnClickListener(v -> {
            Bitmap icon = getBitmapFromView(linearLayout);
            System.out.println("bitmap=="+icon);
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/jpeg");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            int randam = new Random().nextInt(2000);
            File f = new File(config.file.getAbsolutePath()+"/ipl"+"img_file"+randam+".jpg");
            try {
                f.createNewFile();
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(bytes.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
            share.putExtra(Intent.EXTRA_STREAM, Uri.parse(f.getAbsolutePath()));
            startActivity(Intent.createChooser(share, "Share Image"));
        });
        wallpepar.setOnClickListener(v -> {
            builder.show();
        });
    }

    public Palette createPaletteSync(Bitmap bitmap) {
        Palette p = Palette.from(bitmap).generate();
        return p;
    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }
}