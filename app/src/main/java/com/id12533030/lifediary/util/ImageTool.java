package com.id12533030.lifediary.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

/**
 * Created by LENOVO on 2016/6/2.
 */
public class ImageTool {

    public ImageTool(){}

    public static void showImage(String url, ImageView imageView) throws IOException {
        File fs = new File(url);
        if (fs.exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 5;
            Bitmap btp = BitmapFactory.decodeFile(fs.getAbsolutePath(), options);
            imageView.setImageBitmap(btp);
        }
    }
}
