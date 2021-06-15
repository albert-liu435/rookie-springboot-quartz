package com.rookie.bigdata.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

/**
 * @ClassName ScheduleConfig
 * @Description ScheduleConfig
 * @Author rookie
 * @Date 2021/6/15 11:13
 * @Version 1.0
 */
@Configuration
public class ScheduleConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {

        String activeProfile = ApplicationContextUtils.getActiveProfile();

        SchedulerFactoryBean factory = new SchedulerFactoryBean();

//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz-" + activeProfile + ".properties"));
//        propertiesFactoryBean.afterPropertiesSet();
//
//        factory.setQuartzProperties(propertiesFactoryBean.getObject());
        factory.setConfigLocation(new ClassPathResource("/quartz-" + activeProfile + ".properties"));
        return factory;
    }
}
