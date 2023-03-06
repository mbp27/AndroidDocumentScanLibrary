package com.scanlibrary;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jhansi on 05/04/15.
 */
public class Utils {
    static Date currentTime;
    private Utils() {

    }

    public static Uri getUri(Context context, Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        Log.wtf("PATH", "before insertImage");
        // String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title" + " - " + (currentTime = Calendar.getInstance().getTime()), null);
        Log.wtf("PATH", path);
        return Uri.parse(path);
    }

    public static Bitmap getBitmap(Context context, Uri uri) throws IOException {
        return MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
    }

    public static Bitmap scaleBitmap(Context context, Bitmap mBitmap) {
        // Get Screen width
        DisplayMetrics displaymetrics = new DisplayMetrics();
        Activity activity = (Activity) context;
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int ScaleSize = displaymetrics.widthPixels; //max Height or width to Scale
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        float excessSizeRatio = width > height ? (float)((float)width / (float)ScaleSize) : (float)((float)height / (float)ScaleSize);
        Bitmap bitmap = Bitmap.createScaledBitmap(
                mBitmap,(int) (width/excessSizeRatio),(int) (height/excessSizeRatio), true);
        //mBitmap.recycle(); if you are not using mBitmap Obj
        return bitmap;
    }
}