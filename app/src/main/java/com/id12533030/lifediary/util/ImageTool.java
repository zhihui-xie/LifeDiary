package com.id12533030.lifediary.util;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by LENOVO on 2016/6/2.
 * This class is used to process image.
 */
public class ImageTool {
    private AppCompatActivity mAppCompatActivity;

    /**
     * Constructor of ImageTool
     *
     * @param appCompatActivity
     */
    public ImageTool(AppCompatActivity appCompatActivity) {
        mAppCompatActivity = appCompatActivity;
    }

    /**
     * Set the photo the to ImageView based on the url. Developer can set the different size of photo
     *
     * @param url
     * @param imageView
     * @param width
     * @param height
     * @throws IOException
     */
    public static void showImage(String url, ImageView imageView, int width, int height) throws IOException {
        File fs = new File(url);
        //Check whether the photo exists or not
        if (fs.exists()) {
            Bitmap btp = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(fs.getAbsolutePath()), width, height, true);
            imageView.setImageBitmap(btp);
        }
    }

    /**
     * Return the photo as bitmap format, based on url
     *
     * @param url
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
    public Bitmap getBitmap(String url, int width, int height) throws IOException {
        File fs = new File(url);
        Bitmap btp = null;
        if (fs.exists()) {
            btp = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(fs.getAbsolutePath()), width, height, true);
        }
        return btp;
    }

    /**
     * Defined an intent to list photo for users to choose
     */
    public void gallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        mAppCompatActivity.startActivityForResult(intent, Constants.PHOTO_REQUEST_GALLERY);
    }

    /**
     * Allow users to crop the photos
     *
     * @param uri
     */
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

    /**
     * Save bitmap to file in order to store photo into a folder
     *
     * @param bitmap
     * @param path
     * @param name
     */
    public static void saveBitmapTOFile(Bitmap bitmap, String path, String name) {
        FileOutputStream fileOutputStream = null;
        String fileName = path + name + Constants.PIC_FORMAT;
        try {
            File file = new File(fileName);
            File filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
