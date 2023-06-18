package com.dbwp031.dylc.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextServe implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext);
        ApplicationContextServe.applicationContext = applicationContext;

    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
