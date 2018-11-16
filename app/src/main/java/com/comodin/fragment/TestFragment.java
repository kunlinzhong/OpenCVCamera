package com.comodin.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.TextView;

import com.comodin.opencvcamera.BuildConfig;
import com.comodin.opencvcamera.R;
import com.comodin.struct.FunctionsManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.test_fragment_layout)
public class TestFragment extends Fragment {

    @FragmentArg
    String messageStr;
    @FragmentArg
    String messageData;
    @ViewById
    TextView titleTv;

    @AfterViews
    void setData(){
        if(BuildConfig.DEBUG){
            Log.e("setData","----->");
            Log.e("messageStr","----->"+messageStr);
            Log.e("messageData","----->"+messageData);
            FunctionsManager.getInstance2();
        }
        titleTv.setText("messageData");
    }

}

