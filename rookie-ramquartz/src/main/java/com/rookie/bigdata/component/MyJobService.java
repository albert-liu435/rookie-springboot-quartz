package com.rookie.bigdata.component;

import org.springframework.stereotype.Component;

/**
 * @ClassName MyJobService
 * @Description MyJobService
 * @Author rookie
 * @Date 2021/6/15 14:13
 * @Version 1.0
 */
@Component("myJob")
public class MyJobService {



    public void runJob1(){

        System.out.println("runJob1");
    }

    public void runJob2(){
        System.out.println("runJob2");

    }

}
