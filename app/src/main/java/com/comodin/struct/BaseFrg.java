package com.comodin.struct;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.comodin.opencvcamera.MainActivity;
import com.comodin.opencvcamera.MainActivity_;

public class BaseFrg extends Fragment {

    protected FunctionsManager functionsManager;
    private MainActivity mainActivity;
    public void setFunctionManager(FunctionsManager functionsManager){
        this.functionsManager=functionsManager;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity_){
            mainActivity= (MainActivity) context;
            mainActivity.setFunctionsForFragment(getTag());
        }
    }


}
