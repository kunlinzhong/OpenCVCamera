package com.comodin.struct;

public abstract  class FunctionWithResultOnly<Result> extends Function {
    public FunctionWithResultOnly(String funtionName) {
        super(funtionName);
    }
    public abstract Result function();
}
