package com.xfimti.pms.service;

import com.xfimti.pms.pojo.User;

/**
 * <pre>
 * 类的说明：
 * 管理员相关的业务
 *
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-20 10:52
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public interface UserService {
    /**
     * 管理员注册
     * @param user
     */
    void register(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return
     *   true:为可用，false为不可用
     */
    boolean checkUserName(String username);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    LoginStatus login(String username, String password);

    /**
     * 密码找回功能
     * @param username
     *          用户名
     * @param question
     *          密保问题
     * @param answer
     *          密保答案
     * @return
     *          返回User对象，如果为空，则表示找回密码失败，否则返回User对象
     *
     */
    User findUserPassword(String username, String question, String answer);

}
