package com.rookie.bigdata.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ApplicationContextUtils
 * @Description ApplicationContextUtils
 * @Author rookie
 * @Date 2021/5/11 14:54
 * @Version 1.0
 */
@Configuration
public class ApplicationContextUtils implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(ApplicationContextUtils.class);

    public static String ACTIVE_PROFILES = null;
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (null == ApplicationContextUtils.applicationContext) {
            ApplicationContextUtils.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static String getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }
}