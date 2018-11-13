package com.comodin.util;

import android.util.Log;

public class StringUtil {
    public static void printStr(String tag, String msg){
        if(msg.length() > 4000) {
            for(int i=0;i<msg.length();i+=4000){
                if(i+4000<msg.length())
                    Log.d(tag+i,msg.substring(i, i+4000));
                else
                    Log.d(tag+i,msg.substring(i, msg.length()));
            }
        } else
            Log.d(tag,""+msg);
    }

    /**
     * 字符串非空判断
     * @param str
     * @return  true:字符串为空； false字符串非空
     */
    public static Boolean isEmpty(String str){
        if(str==null||str.isEmpty()||str.equalsIgnoreCase("")||str.equalsIgnoreCase("null"))
            return true;
        return false;
    }

    /**
     * 过滤特殊符号
     */
    public static String replaceEmpty(String str){
        String result = "";
        result = str.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
        result.replaceAll("\\D+","").replaceAll("\r", "").replaceAll("\n", "").trim();
        return result;
    }

//    /**
//     * 取消科学计数法显示
//     * @param str
//     * @return
//     */
//    public static String getNumStr(String str){
//        BigDecimal bd = new BigDecimal(str);
//        return bd.toPlainString();
//    }


    public static String addEmpty(String s){//字符串4位加一个空格
        StringBuilder str = new StringBuilder(s.replace(" ",""));
        int i = str.length() / 4;
        int j = str.length() % 4;
        for (int x = (j == 0 ? i - 1 : i); x > 0; x--) {
            str = str.insert(x * 4," ");
        }
        return String.valueOf(str);
    }

    public static String uppercaseStr(String str, int length) {
        if(EmptyUtil.isNotEmpty(str)&&str.length()>length){
            str = str.substring(0, length).toUpperCase() + str.substring(length);
        }
        return  str;
    }
}
