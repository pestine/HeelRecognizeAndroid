package com.example.testapplication;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Connection {
    private static final String TAG = "led";
    private OkHttpClient okHttpClient = new OkHttpsClient().getOkHttpClient();
    private String root = "https://8.129.217.181:443/";

    public Connection() {
    }

    public Connection(String url) {
        root = url;
    }

    public void getRecognizeTestSync() {
        new Thread() {
            @Override
            public void run() {
                Request request = new Request.Builder().url(root + "recognize/test").build();
                Call call = okHttpClient.newCall(request);
                try {
                    Log.i(TAG, ": try connection\n");
                    Response response = call.execute();
                    Log.i(TAG, "getRecognizeTestSync: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void postRecognizeUrlSync(String url) {
        new Thread() {
            @Override
            public void run() {
                FormBody formBody = new FormBody.Builder()
                        .add("leftAnkleURL", "None")
                        .add("rightAnkleURL", "None")
                        .add("doubleAnkleURL", ""+url)
                        .build();
                Request request = new Request.Builder().url(root + "recognize/uploadUrl").post(formBody).build();
                Call call = okHttpClient.newCall(request);
                try {
                    Log.i(TAG, ": try connection\n");
                    Response response = call.execute();
                    Log.e(TAG, "postRecognizeUrlSync: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void postRecognizeImageSync(String filePath) {
        new Thread() {
            @Override
            public void run() {
                File image = new File(""+filePath);
                MultipartBody multipartBody = new MultipartBody.Builder()
                        .addFormDataPart("image", image.getName(), RequestBody.create(image, MediaType.parse("image/jpeg")))
                        .build();
                Request request = new Request.Builder().url(root + "recognize/uploadImage").post(multipartBody).build();
                Call call = okHttpClient.newCall(request);
                try {
                    Log.i(TAG, ": try connection\n");
                    Response response = call.execute();
                    Log.e(TAG, "postRecognizeUrlSync: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public String drawable2File() {
        String path = "";
        return path;
    }

}