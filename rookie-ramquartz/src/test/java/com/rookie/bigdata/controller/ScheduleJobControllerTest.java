package com.rookie.bigdata.controller;

import com.google.gson.Gson;
import com.rookie.bigdata.entity.ScheduleJob;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ClassName ScheduleJobControllerTest
 * @Description ScheduleJobControllerTest
 * @Author rookie
 * @Date 2021/6/15 14:39
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class ScheduleJobControllerTest {

    /**
     * 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。
     */
    private MockMvc mockMvc;

    /**
     * 注入WebApplicationContext
     */
    @Autowired
    private WebApplicationContext wac;

    /**
     * 类加载前
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * 类加载后
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * 实例创建前
     */
    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * 实例创建后
     */
    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void addScheduleJob() throws Exception {

        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setJobName("test1");
        scheduleJob.setJobGroup("test1");
        scheduleJob.setJobCron("0 0/2 15 * * ? ");
        scheduleJob.setBeanName("myJob");
        scheduleJob.setIsConcurrent("1");
        scheduleJob.setMethodName("runJob2");
        scheduleJob.setStatus("1");
        scheduleJob.setJobDesc("描述信息");


        //campainChannel.setCreateDate("2021-04-21 12:12:12");

        System.out.println(new Gson().toJson(scheduleJob));
        MvcResult result = mockMvc.perform(post("/schedulejob/save")
                .contentType(MediaType.APPLICATION_JSON)
                //设置请求体参数
                .content(new Gson().toJson(scheduleJob)))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());


    }

    @Test
    public void deleteScheduleJob() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/schedulejob/delete/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void updateScheduleJob() throws Exception {

        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setId(4);
        scheduleJob.setJobName("test1");
        scheduleJob.setJobGroup("test1");
        scheduleJob.setJobCron("0 0/2 15 * * ? ");
        scheduleJob.setBeanName("myJob");
        scheduleJob.setIsConcurrent("1");
        scheduleJob.setMethodName("runJob2");
        scheduleJob.setStatus("1");
        scheduleJob.setJobDesc("描述信息");


        //campainChannel.setCreateDate("2021-04-21 12:12:12");

        System.out.println(new Gson().toJson(scheduleJob));
        MvcResult result = mockMvc.perform(post("/schedulejob/save")
                .contentType(MediaType.APPLICATION_JSON)
                //设置请求体参数
                .content(new Gson().toJson(scheduleJob)))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void runScheduleJobNow() {



    }
}