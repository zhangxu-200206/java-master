package com.xfimti.pms.controller; /**
 * <pre>
 * 类的说明：
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-22 9:36
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */



import com.alibaba.fastjson.JSON;
import com.xfimti.pms.pojo.Employee;
import com.xfimti.pms.service.EmployeeService;
import com.xfimti.pms.service.PageModel;
import com.xfimti.pms.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "EmpListController", value = "/emp/list")
public class EmpListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //String callback = request.getParameter("callback");//前台必须传递回调函数作为参数
        String selectCondition = request.getParameter("selectCondition");
        String selectKeyWord = request.getParameter("selectKeyWord");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");

        EmployeeService biz = new EmployeeServiceImpl();
        PageModel<Employee> pm = biz.findEmpByPage(selectCondition, selectKeyWord, Integer.parseInt(pageNo), Integer.parseInt(pageSize));

        JsonResult jr = JsonResult.SUCCESS("查询成功", pm);


        //response.addHeader("Access-Control-Allow-Origin","*");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(JSON.toJSONString(jr));

        //response.getWriter().println(callback+"("+JSON.toJSONString(jr)+")");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
