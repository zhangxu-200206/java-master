package com.xfimti.pms.service.impl;

import com.xfimti.pms.dao.EmployeeDao;
import com.xfimti.pms.dao.impl.EmployeeDaoImpl;
import com.xfimti.pms.pojo.Employee;
import com.xfimti.pms.service.EmployeeService;
import com.xfimti.pms.service.PageModel;

import java.sql.Date;
import java.util.List;

/**
 * <pre>
 * 类的说明：
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-20 15:04
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    @Override
    public void saveEmp(Employee employee) {
             /*   1、如果employee没有id,表示新增
                  2.如果employee有id，表示修改*/
        if(employee.getId()!=null){
            employeeDao.update(employee);
        }else{
            employeeDao.insert(employee);
        }

    }

    @Override
    public Employee findEmpInfo(Integer id) {
        return employeeDao.selectById(id);
    }

    @Override
    public void delEmp(Integer id) {
        employeeDao.delete(id);
    }

    public void delEmp(Integer[] id) {
        employeeDao.delete(id);
    }

    @Override
    public PageModel<Employee> findEmpByPage(String condition, String keyword, int pageNo, int pageSize) {
        List<Employee> employees = null;
        int total = 0;
        switch (condition){
            case BY_NAME:
                employees = employeeDao.selectListByName(keyword, pageNo, pageSize);
                total = employeeDao.countByName(keyword);
                break;
            case BY_GENDER:
                employees = employeeDao.selectListByGender(keyword, pageNo, pageSize);
                total = employeeDao.countByGender(keyword);
                break;
            case BY_HIREDATE:
                employees = employeeDao.selectListByHiredate(Date.valueOf(keyword), pageNo, pageSize);
                total = employeeDao.countByHiredate(Date.valueOf(keyword));
                break;
            default:
                employees = employeeDao.selectList(pageNo, pageSize);
                total = employeeDao.count();
                break;
           /* case ALL:
                employees = employeeDao.selectList(pageNo, pageSize);
                total = employeeDao.count();
                break;
            default:
                throw new RuntimeException("不支持的查询条件：请使用EmployeeService中的ALL, BY_NAME, BY_GENDER, BY_HIREDATE四个常量之一");*/


        }

        return new PageModel<Employee>(employees, total, pageNo, pageSize);
    }
}
