package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "led";
    private String reupload = "Reupload";

    private String upload;
    private ImageView image_box;
    private ProgressBar upload_progress;
    private Button upload_button;
    private TextView app_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestMyPermissions();

        app_description = findViewById(R.id.app_description);
        image_box = findViewById(R.id.image_box);
        upload_progress = findViewById(R.id.upload_progress);
        upload_button = findViewById(R.id.upload_button);
        upload = upload_button.getText().toString();

        upload_progress.setVisibility(View.GONE);
    }

    private void requestMyPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有读写授权，编写申请权限代码
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
            Log.e(TAG, "requestMyPermissions: 申请读写SD权限");
        } else if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            Log.e(TAG, "requestMyPermissions: 申请读SD权限");
        } else if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            Log.e(TAG, "requestMyPermissions: 申请写SD权限");
        }
    }

    public void uploadClick(View view){
        Log.e(TAG, ": onClick\n");
        // show progress
        if (upload_button.getText().toString() == upload){
            upload_progress.setVisibility(View.VISIBLE);
            upload_button.setText(reupload);
            Connection connection = new Connection();
            connection.postRecognizeImageSync("/mnt/sdcard/Pictures/IMG_20210506_100637.jpg");
            image_box.setImageResource(R.drawable.image2);
        }else{
            upload_progress.setVisibility(View.GONE);
            upload_button.setText(upload);
            image_box.setImageResource(R.drawable.image1);
        }

    }

}