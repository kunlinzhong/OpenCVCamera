package com.lib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//作用在方法上
@Retention(RetentionPolicy.RUNTIME)//在jvm运行时通过反射获取注释的值
public @interface IPermissions {
    int value();
}
