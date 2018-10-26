package com.lib.annotation;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import com.lib.base.PermissionActivity;
import com.lib.helper.PermissionHelper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理器
 */
public class PermissionManager {
    public static boolean hasPermissions(Activity activity,String... perms) {
        if (activity == null){
            try {
                throw new IllegalAccessException("Activity is null");
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }
        for(String str:perms){
            if(ContextCompat.checkSelfPermission(activity,str)!= PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    public static void requestPermissions(Activity activity,String  rationale,int requestCode,String... perms){
        if(hasPermissions(activity,perms)){
            notifyhasPermissions(activity,requestCode,perms);
            return;
        }
        //权限申请
        PermissionHelper permissionHelper = PermissionHelper.newInstance(activity);
        permissionHelper.requestPermissions(rationale,android.R.string.ok,android.R.string.cancel,requestCode,perms);
    }

    private static void notifyhasPermissions(Activity activity,int requestCode,String... perms){
        int[] grantResults = new int[perms.length];
        for (int i=0;i<perms.length;i++){
            grantResults[i] = PackageManager.PERMISSION_GRANTED;
        }
        onRequestPermissionResult(activity,requestCode,perms,grantResults);
    }
    public static void onRequestPermissionResult(Activity activity,int requestCode,String[] perms,int[] grantResults){
        List<String> granted = new ArrayList<>();
        List<String> denied = new ArrayList<>();
        for (int i = 0;i<perms.length;i++){
            String perm = perms[i];
            if (grantResults[i]==PackageManager.PERMISSION_GRANTED){
                granted.add(perm);
            }else{
                denied.add(perm);
            }
        }
        if (!granted.isEmpty()){
            if(activity instanceof  PermisssionCallback){
                ((PermisssionCallback)activity).onPermissionGranted(requestCode,granted);;
            }
        }
        if(!denied.isEmpty()){
            ((PermisssionCallback)activity).onPermissionDenied(requestCode,denied);;
        }
        if(!granted.isEmpty() && denied.isEmpty()){
        reflectAnnotationMethod(activity,requestCode);
        }
    }

    private static void reflectAnnotationMethod(Activity activity,int requestCode){
        Class<? extends Activity> clazz = activity.getClass();
        Method[] methods =clazz.getDeclaredMethods();
        for (Method method:methods){
            if(method.isAnnotationPresent(IPermissions.class)){
                IPermissions iPermissions = method.getAnnotation(IPermissions.class);
                if(iPermissions.value()==requestCode){
                    if(method.getParameterTypes().length>0){
                        throw new RuntimeException("RuntimeException");
                    }
                    try {
                        if(method.isAccessible())
                            method.setAccessible(true);
                        method.invoke(activity);
                    }catch (IllegalAccessException|InvocationTargetException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static boolean somePermissionPermanentlyDenied(PermissionActivity permissionActivity, List<String> perms) {
        return PermissionHelper.newInstance(permissionActivity).somePermissionPermanentlyDenied(perms);
    }
}
