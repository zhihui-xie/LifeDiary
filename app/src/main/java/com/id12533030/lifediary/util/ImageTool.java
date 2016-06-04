package com.id12533030.lifediary.util;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by LENOVO on 2016/6/2.
 */
public class ImageTool {
    private AppCompatActivity mAppCompatActivity;
    public ImageTool(AppCompatActivity appCompatActivity){
        mAppCompatActivity = appCompatActivity;
    }

    public static void showImage(String url, ImageView imageView, int width, int height) throws IOException{
        File fs = new File(url);
        if (fs.exists()) {
            Bitmap btp = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(fs.getAbsolutePath()), width, height, true);
            imageView.setImageBitmap(btp);
        }
    }

    public Bitmap getBitmap(String url, int width, int height) throws IOException {
        File fs = new File(url);
        Bitmap btp = null;
        if (fs.exists()) {
            btp = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(fs.getAbsolutePath()), width, height, true);
        }
        return btp;
    }



    public void gallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        mAppCompatActivity.startActivityForResult(intent, Constants.PHOTO_REQUEST_GALLERY);
    }

    public void crop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);

        mAppCompatActivity.startActivityForResult(intent, Constants.PHOTO_REQUEST_CUT);
    }


    public static void saveBitmapTOFile(Bitmap bitmap, String path, String name) {
        FileOutputStream fileOutputStream = null;
        String fileName = path + name + Constants.PIC_FOMATE;
        try{
            File file = new File (fileName);
            File filePath = new File (path);
            if (!filePath.exists()){
                filePath.mkdirs();
            }
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
