package com.comodin.opencvcamera;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.comodin.fragment.TestFragment;
import com.comodin.fragment.TestFragment_;
import com.lib.annotation.PermissionManager;
import com.lib.base.PermissionActivity;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

@EActivity(R.layout.activity_main)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class MainActivity extends PermissionActivity {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    private boolean fragmentFlag = true;
    private final static int CAMERA_CODE = 111;
    private final static int LOCATION_CODE = 222;
    private String[] permissions = {Manifest.permission.CAMERA,Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO};
    @ViewById
    TextView sample_text;
    @ViewById
    LinearLayout title_layout;
    @ViewById
    Button btn_fragment;
    @ViewById
    Button btn_opencv_test;
    @ViewById
    Button btn_player;
    @ViewById
    Button btn_custom_camera_view;
    private TestFragment testFragment;
    @AfterViews
    void init(){
        sample_text.setText(stringFromJNI());
    }

    @Click(R.id.btn_fragment)
    void onClickAddFragment() {
        if (fragmentFlag) {//加载fragment
            if(testFragment!=null&&testFragment.isAdded()){
                Toast.makeText(this,"Test Fragment isAdded",Toast.LENGTH_LONG).show();
            }else{
                testFragment = new TestFragment_();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.add(R.id.title_layout, testFragment);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
            }


        } else if (PermissionManager.hasPermissions(MainActivity.this, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE)) {
            Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            videoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Camera/");//保存路径
            videoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);//分辨率0最低，1最高
            startActivityForResult(videoIntent, 0); //开启系统摄像机
        } else {
            PermissionManager.requestPermissions(MainActivity.this, "need camera permission", CAMERA_CODE, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE);
        }
    }
    @Click(R.id.btn_custom_camera_view)
    void onClickCustomCamera(){
        Intent intent = new Intent(this,CustomCamera.class);
        startActivity(intent);
    }
    @Click(R.id.btn_opencv_test)
    void onClickOpenCVTestBtn(){
        Intent intent = new Intent(this,SCameraActivity.class);
        startActivity(intent);
//        OpencvTestActivity_.intent(this).start();
    }
    @Click(R.id.btn_player)
    void onClickOpenPlayer(){
        MyPlayerActivity_.intent(this).start();
    }

    @OnActivityResult(0)
    void onResult(int resultCode, Intent data) {
        Log.i("onActivityResult-->", "拍摄完成，resultCode=" + resultCode);
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

//    private Button btn_camera;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
////        setContentView(R.layout.activity_main);
//
//        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
//        initView();
//    }
//    private static final String FILE_PATH = "/sdcard/sysvideocamera.3gp";
//    private void initView() {
////        checkPermissions(permissions);
//        btn_camera = (Button) findViewById(R.id.btn_camera);
//        btn_camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(PermissionManager.hasPermissions(MainActivity.this,Manifest.permission.CAMERA,Manifest.permission.READ_PHONE_STATE)) {
//                    Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//                    //保存路径
//                    videoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Camera/");
//                    //分辨率0最低，1最高
//                    videoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//                    //开启系统摄像机
//                    startActivityForResult(videoIntent, 0);
//                }else{
//                    PermissionManager.requestPermissions(MainActivity.this,"need camera permission",CAMERA_CODE,Manifest.permission.CAMERA,Manifest.permission.READ_PHONE_STATE);
//                }
//            }
//        });
//
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.i("onActivityResult", "拍摄完成，resultCode=" + requestCode);
//    }
}
