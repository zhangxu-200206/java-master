package com.xfimti.pms.controller; /**
 * <pre>
 * 类的说明：
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-22 10:42
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */

import com.alibaba.fastjson.JSON;
import com.xfimti.pms.exception.DataAccessException;
import com.xfimti.pms.pojo.Employee;
import com.xfimti.pms.service.EmployeeService;
import com.xfimti.pms.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "EmpSaveController", value = "/emp/save")
public class EmpSaveController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("utf-8");

        String id = request.getParameter("id");
        String empName = request.getParameter("empName");
        String gender = request.getParameter("gender");
        String salary = request.getParameter("salary");
        String birthday = request.getParameter("birthday");
        String hiredate = request.getParameter("hiredate");


        EmployeeService biz = new EmployeeServiceImpl();
        Employee employee = new Employee();
        //修改是有ID，保存是没有ID
        if(id != null && !"".equals(id)){
            employee.setId(Integer.parseInt(id));
        }
        employee.setEmpName(empName);
        employee.setGender(gender);
        employee.setBirthday(Date.valueOf(birthday));
        employee.setHiredate(Date.valueOf(hiredate));
        employee.setSalary(Double.valueOf(salary));

        JsonResult jr ;
        try{
            biz.saveEmp(employee);
            jr = JsonResult.SUCCESS("保存数据成功", employee);
        }catch (DataAccessException e){
            e.printStackTrace();
            jr = JsonResult.ERROR("保存数据失败", employee);
        }

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(JSON.toJSONString(jr));


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
