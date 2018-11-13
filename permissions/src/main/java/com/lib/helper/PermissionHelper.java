package com.lib.helper;

import android.app.Activity;
import android.os.Build;

import java.util.List;

public abstract class PermissionHelper {
    private Activity activity;
    public PermissionHelper(Activity activity){this.activity = activity;}
    public Activity getHost(){return activity;}
    public static PermissionHelper newInstance(Activity activity){
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return new LowApiPermissionHelper(activity);
        }
        return new ActivityPermissionHelper(activity);
    }

    public abstract void requestPermissions(String rationale, int positiveButton, int negativeButton, int requestCode, String... perms);

    public abstract boolean shouldshowRequestPermissionRationale(String deniedPermission);
    public boolean somePermissionPermanentlyDenied(List<String> deniedPermissions){
        for (String deniedPermission:deniedPermissions) {
            if(!shouldshowRequestPermissionRationale(deniedPermission))
                return true;
        }

        return false;
    }
}
