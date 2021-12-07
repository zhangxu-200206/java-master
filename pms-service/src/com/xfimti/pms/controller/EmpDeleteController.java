package com.xfimti.pms.controller;
/*
  @author 蜜桃百香碎碎冰 
  @create   2021年
  */

import com.xfimti.pms.dao.EmployeeDao;
import com.xfimti.pms.dao.impl.EmployeeDaoImpl;
import com.xfimti.pms.service.EmployeeService;
import com.xfimti.pms.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/emp/del")
public class EmpDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //请求 URL: http://localhost:10086/pms-service/emp/del?id%5B%5D=1
        //这里的%5B%5D 分别是[ 和 ]
        String [] delId;
        delId= req.getParameterValues("id[]");
        EmployeeService biz = new EmployeeServiceImpl();
        //返回数组 请求的数据
        Integer[] ss = new Integer[delId.length];

        for (int i = 0; i < delId.length; i++) {
            ss[i]= Integer.valueOf(delId[i]);

        }

        biz.delEmp(ss);

    }
}
