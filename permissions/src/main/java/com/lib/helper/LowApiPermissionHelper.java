package com.lib.helper;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;

class LowApiPermissionHelper extends PermissionHelper{
    public LowApiPermissionHelper(Activity activity){
        super(activity);
    }

    @Override
    public void requestPermissions(String rationale, int positiveButton, int negativeButton, int requestCode, String... perms) {

    }

    @Override
    public boolean shouldshowRequestPermissionRationale(String deniedPermission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(getHost(),deniedPermission);
    }
}
