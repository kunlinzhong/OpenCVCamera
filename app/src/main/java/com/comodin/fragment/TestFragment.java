package com.comodin.fragment;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import com.comodin.opencvcamera.BuildConfig;
import com.comodin.opencvcamera.MainActivity;
import com.comodin.opencvcamera.MainActivity_;
import com.comodin.opencvcamera.R;
import com.comodin.struct.BaseFrg;
import com.comodin.struct.FunctionsManager;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.test_fragment_layout)
public class TestFragment extends BaseFrg {
    public static final String INTERFACE = TestFragment.class.getName()+"NPNR";
    @FragmentArg
    String messageStr;
    @FragmentArg
    String messageData;
    @ViewById
    TextView titleTv;
    @ViewById
    Button btn_1;

    private MainActivity mBaseActivity;

    @AfterViews
    void setData(){
        if(BuildConfig.DEBUG){
            Log.e("setData","----->");
            Log.e("messageStr","----->"+messageStr);
            Log.e("messageData","----->"+messageData);
            FunctionsManager.getInstance();
        }
        titleTv.setText("messageData");
    }

    @Click(R.id.btn_1)
    void onClickBtn1(){
        functionsManager.invokeFunc(TestFragment.INTERFACE);
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if(context instanceof MainActivity_){
//            mBaseActivity = (MainActivity) context;
//            mBaseActivity.setFunctionsForFragment(getTag());
//        }
//    }
}

