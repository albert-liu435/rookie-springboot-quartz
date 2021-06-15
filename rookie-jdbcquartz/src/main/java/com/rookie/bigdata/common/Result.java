package com.rookie.bigdata.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Description Result
 * @Author rookie
 * @Date 2021/6/15 11:00
 * @Version 1.0
 */
@Data
public class Result implements Serializable {

    private int code; // 200是正常，非200表示异常
    private String msg;
    //成功或者失败
    //SUCCESS/ERROR
    // private String Result;
    private Object data;

    public static Result succ() {
        return succ(200, "操作成功", null);
    }

    public static Result succ(Object data) {
        return succ(200, "操作成功", data);
    }

    public static Result succ(int code, String msg) {
        return succ(code, msg, null);
    }

    public static Result succ(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(String msg) {
        return fail(400, msg, null);
    }

    public static Result fail(int code, String msg) {
        return fail(code, msg, null);
    }


    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
