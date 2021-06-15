package com.rookie.bigdata.schedule;

import com.rookie.bigdata.config.ApplicationContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @ClassName ScheduleRunnable
 * @Description ScheduleRunnable
 * @Author rookie
 * @Date 2021/6/15 11:27
 * @Version 1.0
 */
public class ScheduleRunnable implements Runnable {

    public static final Logger logger = LoggerFactory.getLogger(ScheduleRunnable.class);

    private Object target;
    private Method method;
    private String params;

    public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
        this.target = ApplicationContextUtils.getBean(beanName);
        this.params = params;

        if (StringUtils.isEmpty(params)) {
            this.method = target.getClass().getDeclaredMethod(methodName);

        } else {
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        }
    }

    @Override
    public void run() {

        try {
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isEmpty(params)) {
                method.invoke(target);
            } else {
                method.invoke(target, params);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
