package com.xfimti.pms.controller;

import com.alibaba.fastjson.JSON;
import com.xfimti.pms.service.LoginStatus;
import com.xfimti.pms.service.UserService;
import com.xfimti.pms.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * 类的说明：
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-11-05 10:37
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
@WebServlet("/user/login")
public class UserLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserService biz = new UserServiceImpl();
        LoginStatus result = biz.login(username, password);

        JsonResult jr = null;
        if(result==LoginStatus.SUCCESS){
            jr = JsonResult.SUCCESS("登录成功", 1);
        }else if(result == LoginStatus.USER_NAME_NOT_EXIST){
            jr = JsonResult.ERROR("登录失败", 2);
        }else if(result == LoginStatus.USER_PASSWORD_ERROR){
            jr = JsonResult.ERROR("登录失败", 3);
        }

        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().println(JSON.toJSONString(jr));

    }
}
