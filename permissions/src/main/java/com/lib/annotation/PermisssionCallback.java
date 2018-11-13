package com.lib.annotation;

import java.util.List;

public interface PermisssionCallback {
    void onPermissionGranted(int requestCode, List<String> perms);//权限通过回调
    void onPermissionDenied(int requestCode, List<String> perms);//权限被拒绝回调
}
