package com.comodin.opencvcamera;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lib.annotation.PermissionManager;
import com.lib.base.PermissionActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import java.util.List;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends PermissionActivity {

    private final long SPLASH_DISPLAY_LENGHT = 3000;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//        new Handler().postDelayed(new Runnable(){
//
//            @Override
//            public void run() {
//                checkPermissions();
//            }
//
//        }, SPLASH_DISPLAY_LENGHT);
//
//    }
    @AfterViews
    void afterview(){
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                checkPermissions();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }
    private final static int CAMERA_CODE = 111;
    private void checkPermissions(){
        if (PermissionManager.hasPermissions(this,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO)){
//            Intent intent = new Intent(this,MainActivity_.class);
//            startActivity(intent);
            MainActivity_.intent(this).start();
        }else{
            PermissionManager.requestPermissions(this,"please set permission!",
                    CAMERA_CODE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO);
        }
    }

    @Override
    public void onPermissionGranted(int requestCode, List<String> perms) {
        super.onPermissionGranted(requestCode, perms);
        checkPermissions();
    }

    @Override
    public void onPermissionDenied(int requestCode, List<String> perms) {
        super.onPermissionDenied(requestCode, perms);
        Log.e("onPermissionDenied","onPermissionDenied");
    }
}
