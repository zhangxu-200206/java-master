package com.xfimti.pms.controller;

/**
 * <pre>
 * 类的说明：
 *    专门用来进行接口之间数据传递
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-11-05 10:53
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public class JsonResult {
    private int code;
    private String message;
    private Object data;

    public static JsonResult SUCCESS(String message){
        JsonResult jr = new JsonResult();
        jr.setCode(200);
        jr.setMessage(message);
        return jr;
    }

    public static JsonResult SUCCESS(String message, Object data){
        JsonResult jr = new JsonResult();
        jr.setCode(200);
        jr.setMessage(message);
        jr.setData(data);
        return jr;
    }

    public static JsonResult ERROR(String message){
        JsonResult jr = new JsonResult();
        jr.setCode(500);
        jr.setMessage(message);
        return jr;
    }

    public static JsonResult ERROR(String message, Object data){
        JsonResult jr = new JsonResult();
        jr.setCode(500);
        jr.setMessage(message);
        jr.setData(data);
        return jr;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
