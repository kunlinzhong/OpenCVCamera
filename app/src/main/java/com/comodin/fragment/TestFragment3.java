package com.comodin.fragment;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.comodin.opencvcamera.BuildConfig;
import com.comodin.opencvcamera.R;
import com.comodin.struct.BaseFrg;
import com.comodin.struct.FunctionsManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.test_fragment_3_layout)
public class TestFragment3 extends BaseFrg {
    public static final String INTERFACE = TestFragment.class.getName()+"NPNR";
    @FragmentArg
    String messageStr;
    @FragmentArg
    String messageData;
    @ViewById
    TextView titleTv3;
    @ViewById
    Button btn_3;

    @AfterViews
    void setData(){
        if(BuildConfig.DEBUG){
            Log.e("setData","---3-->");
            Log.e("messageStr","---3-->"+messageStr);
            Log.e("messageData","---3-->"+messageData);
            FunctionsManager.getInstance();
        }
        titleTv3.setText("messageData3");
    }

    @Click(R.id.btn_3)
    void onClickBtn(){

    }

}