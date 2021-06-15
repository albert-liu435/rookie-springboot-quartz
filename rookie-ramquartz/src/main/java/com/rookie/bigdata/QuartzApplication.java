package com.rookie.bigdata;

import com.rookie.bigdata.config.ApplicationContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

/**
 * @ClassName QuartzApplication
 * @Description QuartzApplication
 * @Author rookie
 * @Date 2021/6/15 10:42
 * @Version 1.0
 */
@SpringBootApplication
@EnableTransactionManagement
public class QuartzApplication {
    public static final Logger logger = LoggerFactory.getLogger(QuartzApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);


        if (!StringUtils.isEmpty(ApplicationContextUtils.getActiveProfile()) && ApplicationContextUtils.getActiveProfile().equals("dev")) {
            logger.info("启动应用环境为: dev");
        } else if (!StringUtils.isEmpty(ApplicationContextUtils.getActiveProfile()) && ApplicationContextUtils.getActiveProfile().equals("uat")) {
            logger.info("启动应用环境为:uat");
        } else {
            logger.info("启动应用环境为:prod");
        }


    }
}
