package com.lib.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

public class AppSettingDialog implements DialogInterface.OnClickListener{
    private Activity activity;
    private String title;
    private String rational;
    private String positiveButton;
    private String negativeButton;
    private DialogInterface.OnClickListener listener;
    private int requestCode;
    public final static int SETTING_CODE = 333;

    private AppSettingDialog(Activity activity,
                             String title,
                             String rational,
                             String positiveButton,
                             String negativeButton,
                             DialogInterface.OnClickListener listener,int requestCode){
        this.activity = activity;
        this.title = title;
        this.rational = rational;
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
        this.listener = listener;
        this.requestCode = requestCode;
    }
    public void show(){
        if(listener != null){
            showDialog();
        }else{
            try {
                throw new IllegalAccessException("");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    private void showDialog(){
        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(rational)
                .setPositiveButton(positiveButton,this)
                .setNegativeButton(negativeButton,listener)
                .setCancelable(false)
                .create()
                .show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {//跳转到设置
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",activity.getPackageName(),null);
        intent.setData(uri);
        activity.startActivityForResult(intent,requestCode);
    }
  public static class Builder{
      private Activity activity;
      private String title;
      private String rational;
      private String positiveButton;
      private String negativeButton;
      private DialogInterface.OnClickListener listener;
      private int requestCode = -1;
      public final static int SETTING_CODE = 333;
      public Builder(Activity activity){
          this.activity = activity;
      }
      public Builder setLister(DialogInterface.OnClickListener listener){
          this.listener = listener;
          return this;
      }
      public Builder setRequestCode(int requestCode){
          this.requestCode = requestCode;
          return this;
      }
      public AppSettingDialog build(){
          title = "权限设置";
          rational = "跳转到设置界面设置权限";
          positiveButton = activity.getString(android.R.string.ok);
          negativeButton = activity.getString(android.R.string.cancel);
          return new AppSettingDialog(activity,
                  title,
                  rational,
                  positiveButton,
                  negativeButton,
                  listener,
                  requestCode);
      }
  }
}
