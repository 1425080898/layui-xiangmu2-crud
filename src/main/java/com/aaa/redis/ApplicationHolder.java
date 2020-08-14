package com.aaa.redis;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContextStatic;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContextStatic=applicationContext;
    }

    public static ApplicationContext getApplicationContextStatic() {
        return applicationContextStatic;
    }
}
