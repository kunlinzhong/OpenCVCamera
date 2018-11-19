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

@EFragment(R.layout.test_fragment_2_layout)
public class TestFragment2 extends BaseFrg {
    public static final String INTERFACE = TestFragment.class.getName()+"NPNR";
    @FragmentArg
    String messageStr;
    @FragmentArg
    String messageData;
    @ViewById
    TextView titleTv2;
    @ViewById
    Button btn_2;

    @AfterViews
    void setData(){
        if(BuildConfig.DEBUG){
            Log.e("setData","---2-->");
            Log.e("messageStr","---2-->"+messageStr);
            Log.e("messageData","---2-->"+messageData);
            FunctionsManager.getInstance();
        }
        titleTv2.setText("messageData2");
    }

    @Click(R.id.btn_2)
    void onClickBtn(){

    }

}