package com.comodin.struct;
import android.text.TextUtils;

import java.util.HashMap;
public class FunctionsManager {
//    private static class A{
//        public static FunctionsManager functionsManager = new FunctionsManager();
//    }
//    public static FunctionsManager getInstance2(){
//        return A.functionsManager;
//    }

    private static FunctionsManager _instance;
    public static FunctionsManager getInstance(){
        if (_instance == null){
            _instance = new FunctionsManager();
        }
        return _instance;
    }
//    public static FunctionsManager getInstance(){
//        if(_instance == null){
//            synchronized (FunctionsManager.class){
//                if (_instance == null){
//                    _instance = new FunctionsManager();
//                }
//            }
//        }
//        return _instance;
//    }
//    private volatile static FunctionsManager _instance;
//    {
//        _instance = new FunctionsManager();
//    }
    private FunctionsManager(){
        mFunctionNoParamNoResult=new HashMap<>();
        mFunctionWithParamOnly=new HashMap<>();
        mFunctionWithResultOnly=new HashMap<>();
        mFunctionWithParamAndResult=new HashMap<>();
    }

    private HashMap<String,FunctionNoParamNoResult> mFunctionNoParamNoResult;
    private HashMap<String,FunctionWithParamOnly> mFunctionWithParamOnly;
    private HashMap<String,FunctionWithResultOnly> mFunctionWithResultOnly;
    private HashMap<String,FunctionWithParamAndResult> mFunctionWithParamAndResult;

    /**
     * 添加无参数无返回值
     * @param function
     * @return
     */
    public FunctionsManager addFunction(FunctionNoParamNoResult function){
        if(mFunctionNoParamNoResult!=null){
            mFunctionNoParamNoResult.put(function.mFuntionName,function);
        }
        return this;
    }

    /**
     * 执行没参数没返回值的
     * @param funcName
     */
    public void invokeFunc(String funcName){
        if(TextUtils.isEmpty(funcName)){
            return;
        }
        if(mFunctionNoParamNoResult!=null){
            FunctionNoParamNoResult functionNoParamNoResult=mFunctionNoParamNoResult.get(funcName);
            if(functionNoParamNoResult!=null){
                functionNoParamNoResult.function();
            }else {
                try {
                    throw new FunctionsException("function not found");
                } catch (FunctionsException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
