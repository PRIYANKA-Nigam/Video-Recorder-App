package com.example.videorecorder;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity { static final int REQUEST_VIDEO_CAPTURE = 1;
    Button b;private View main;private ImageView imageView;private Button bt; @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.button);
        main=findViewById(R.id.rel); imageView=(ImageView)findViewById(R.id.imageView);
        bt=(Button)findViewById(R.id.button2);
        bt.setOnClickListener(new View.OnClickListener() { @Override
            public void onClick(View v) {
            Bitmap b=ScreenShot.SnapOfRootView(imageView);
                imageView.setImageBitmap(b);
                main.setBackgroundColor(Color.parseColor("#999999"));
                if (b!=null){ SaveSnap(b); } }}); }
    public void dispatchTakeVideoIntent(View view) {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE); } }@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            VideoView videoView = new VideoView(this);
            Uri videoUri = intent.getData();videoView.setVideoURI(videoUri);videoView.start();
            builder.setView(videoView).show(); } }
            private void SaveSnap(Bitmap bm){
        ByteArrayOutputStream byteArrayOutputStream=null; File file=null;
        try { byteArrayOutputStream=new ByteArrayOutputStream();bm.compress(Bitmap.CompressFormat.PNG,40,byteArrayOutputStream);
            file=new File(Environment.getExternalStorageDirectory()+file.separator+"player.png");
            file.createNewFile(); FileOutputStream foe=new FileOutputStream(file);
            foe.write(byteArrayOutputStream.toByteArray()); foe.flush();foe.close();
        }catch (Exception e){ e.printStackTrace(); } }


}