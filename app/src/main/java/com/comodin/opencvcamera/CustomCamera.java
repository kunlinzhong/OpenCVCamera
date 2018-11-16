package com.comodin.opencvcamera;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.cameraview.CameraImpl;
import com.google.android.cameraview.CameraView;
import com.lib.annotation.PermissionManager;

import me.pqpo.smartcameralib.CustomCameraView;

public class CustomCamera extends AppCompatActivity {

    private CustomCameraView customCameraView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_camera);
        initView();
    }
    private void initView(){
        customCameraView = findViewById(R.id.custom_camera_view);
        customCameraView.addCallback(new CameraImpl.Callback() {
            @Override
            public void onCameraOpened(CameraImpl camera) {
                super.onCameraOpened(camera);
            }

            @Override
            public void onCameraClosed(CameraImpl camera) {
                super.onCameraClosed(camera);
            }

            @Override
            public void onPictureTaken(CameraImpl camera, byte[] data) {
                super.onPictureTaken(camera, data);
                if (data!=null)
                    Log.e("onPictureTaken-->","-->"+data.length);
            }

            @Override
            public void onPicturePreview(CameraImpl camera, byte[] data) {
                super.onPicturePreview(camera, data);
                if (data!=null)
                    Log.e("onPicturePreview-->","-->"+data.length);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PermissionManager.hasPermissions(this,Manifest.permission.CAMERA)){
            customCameraView.start();
        }
    }

    @Override
    protected void onPause() {
        customCameraView.stop();
        super.onPause();
    }
}
