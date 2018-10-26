package com.comodin.opencvcamera;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lib.annotation.IPermissions;
import com.lib.annotation.PermissionManager;
import com.lib.base.PermissionActivity;

import java.io.File;

public class MainActivity extends PermissionActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private final static int CAMERA_CODE = 111;
    private final static int LOCATION_CODE = 222;
    private Button btn_camera;
    private String[] permissions = {Manifest.permission.CAMERA,Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        initView();
    }

    private static final String FILE_PATH = "/sdcard/sysvideocamera.3gp";

    private void initView() {
//        checkPermissions(permissions);
        btn_camera = (Button) findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PermissionManager.hasPermissions(MainActivity.this,Manifest.permission.CAMERA,Manifest.permission.READ_PHONE_STATE)) {
                    Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    //保存路径
                    videoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Camera/");
                    //分辨率0最低，1最高
                    videoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                    //开启系统摄像机
                    startActivityForResult(videoIntent, 0);
                }else{
                    PermissionManager.requestPermissions(MainActivity.this,"need camera permission",CAMERA_CODE,Manifest.permission.CAMERA,Manifest.permission.READ_PHONE_STATE);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("onActivityResult", "拍摄完成，resultCode=" + requestCode);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
