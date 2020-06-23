package com.aaa.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//说明该注解作用在方法上
@Target(ElementType.METHOD)
//注解在运行时生效
@Retention(RetentionPolicy.RUNTIME)
public @interface SaveOrUpdateEntityAnn {
    Class<?> entityClass();
}
