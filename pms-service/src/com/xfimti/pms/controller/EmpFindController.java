package com.xfimti.pms.controller; /**
 * <pre>
 * 类的说明：
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-22 10:56
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */

import com.alibaba.fastjson.JSON;
import com.xfimti.pms.pojo.Employee;
import com.xfimti.pms.service.EmployeeService;
import com.xfimti.pms.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmpFindController", value = "/emp/get")
public class EmpFindController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        System.out.println(id);
        EmployeeService biz = new EmployeeServiceImpl();
        Employee emp = biz.findEmpInfo(Integer.parseInt(id));

        JsonResult jr = JsonResult.SUCCESS("查询成功", emp);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(JSON.toJSONString(jr));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
