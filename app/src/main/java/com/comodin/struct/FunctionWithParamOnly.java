package com.comodin.struct;

public abstract class FunctionWithParamOnly<Param> extends Function {
    public FunctionWithParamOnly(String funtionName) {
        super(funtionName);
    }
    public abstract void function(Param param);
}
