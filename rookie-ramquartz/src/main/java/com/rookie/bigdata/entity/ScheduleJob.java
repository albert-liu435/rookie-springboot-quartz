package com.rookie.bigdata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 *
 * </p>
 *
 * @author rookie
 * @since 2021-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ScheduleJob implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务唯一ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 任务名称
     */
    @NotEmpty(message = "不能为空")
    private String jobName;

    /**
     * 任务分组
     */
    @NotEmpty(message = "不能为空")
    private String jobGroup;

    /**
     * 任务cron表达式
     */
    @NotEmpty(message = "不能为空")
    private String jobCron;

    /**
     * 执行时调用哪个Bean
     */
    @NotEmpty(message = "不能为空")
    private String beanName;

    /**
     * 是否有状态
     */
    @NotEmpty(message = "不能为空")
    private String isConcurrent;

    /**
     * 任务调用的方法名称
     */
    @NotEmpty(message = "不能为空")
    private String methodName;

    /**
     * 任务状态 0禁用 1启用
     */
    @NotEmpty(message = "不能为空")
    private String status;

    /**
     * 任务描述
     */
    private String jobDesc;

    /**
     * 任务创建时间
     */
    private String createDate;

    /**
     * 任务更新时间
     */
    private String updateTime;


}
