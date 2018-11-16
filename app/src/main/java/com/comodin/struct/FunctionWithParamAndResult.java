package com.comodin.struct;

public abstract class FunctionWithParamAndResult<Param,Result> extends Function {
    public FunctionWithParamAndResult(String funtionName) {
        super(funtionName);
    }
    public abstract Result Function(Param param);
}
