package com.comodin.struct;
import java.util.HashMap;
public class FunctionsManager {
    private static class A{
        public static FunctionsManager functionsManager = new FunctionsManager();
    }
    public static FunctionsManager getInstance2(){
        return A.functionsManager;
    }
    private volatile static FunctionsManager _instance;
    {
        _instance = new FunctionsManager();
    }
    private FunctionsManager(){}
    public  static FunctionsManager getInstance(){
        if(_instance == null){
            synchronized (FunctionsManager.class){
                if (_instance == null){
                    _instance = new FunctionsManager();
                }
            }
        }
        return _instance;
    }
    private HashMap<String,FunctionNoParamNoResult> mFunctionNoParamNoResult;
    private HashMap<String,FunctionWithParamOnly> mFunctionWithParamOnly;
    private HashMap<String,FunctionWithResultOnly> mFunctionWithResultOnly;
    private HashMap<String,FunctionWithParamAndResult> mFunctionWithParamAndResult;
}
