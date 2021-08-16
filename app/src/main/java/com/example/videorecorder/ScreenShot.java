package com.example.videorecorder;

import android.graphics.Bitmap;
import android.view.View;

public class ScreenShot {
    public static Bitmap takeScreen(View v){
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b=Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }
    public static Bitmap SnapOfRootView(View v){
        return  takeScreen(v.getRootView());

    }
}
