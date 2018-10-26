package com.lib.base;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lib.annotation.PermissionManager;
import com.lib.dialog.AppSettingDialog;

import java.util.List;

public class PermissionActivity extends BaseActivity {
    @Override
    public void onPermissionDenied(int requestCode, List<String> perms) {
        if(PermissionManager.somePermissionPermanentlyDenied(this,perms)){
            new AppSettingDialog.Builder(this)
                    .setLister(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.e("PermissionActivity","onclick:");
                        }
                    }).build().show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("PermissionActivity","onActivityResult: "+requestCode);
   }
}
