package com.xfimti.pms.exception;

/**
 * <pre>
 * 类的说明：
 *    数据访问异常
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-19 15:04
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public class DataAccessException extends RuntimeException{
    public DataAccessException(String message){
        super(message);
    }

    public DataAccessException(String message, Throwable throwable){
        super(message, throwable);
    }
}
