package com.comodin.util;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

public class ActivityController {
    public static List<Activity> activityList = new ArrayList<>();
    private static Activity currentActivity;

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }
    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void setCurrentActivity(Activity activity) {
        currentActivity = activity;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }
    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

//    public static void connSocket(){
//        AppClientSocketRequestUtil.openLog();
//        StringUtil.printStr("init webSocket:", String.format("%s%s%s%s%s","\nsocketDomain:", ConstUrl.socketDomain,"\nzuulDomain:",ConstUrl.zuulDomain,"\nDeviceNo:"+ DeviceUtil.getDeviceNo()));
//        String Token = PreferenceHelper.getToken();
//        String userId = PreferenceHelper.getUserId()==0?null: String.valueOf(PreferenceHelper.getUserId());
//        Log.e("Token","--->"+Token);
//        if(EmptyUtil.isNotEmpty(userId))
//            AppClientSocketRequestUtil.init(ConstUrl.socketDomain,ConstUrl.zuulDomain, DeviceUtil.getDeviceNo(),Token, Long.parseLong(userId),null, SocketConstant.SOCKET_DEFAULT_RSA_KEY, packageUtil.getListener());
//        else
//            AppClientSocketRequestUtil.init(ConstUrl.socketDomain,ConstUrl.zuulDomain, DeviceUtil.getDeviceNo(),Token,null,null, SocketConstant.SOCKET_DEFAULT_RSA_KEY, packageUtil.getListener());
//    }
}
