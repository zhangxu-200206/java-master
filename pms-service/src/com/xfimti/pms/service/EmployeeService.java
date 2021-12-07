package com.xfimti.pms.service;

import com.xfimti.pms.pojo.Employee;

/**
 * <pre>
 * 类的说明：
 *   员工管理相关的业务
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-20 14:06
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public interface EmployeeService {
    //3个常量表示查询的条件
    String ALL = "0";       //按名称查
    String BY_NAME = "1";       //按名称查
    String BY_GENDER = "2";     //按性别查
    String BY_HIREDATE = "3";   //按入职日期查

    /**
     * 保存员工信息
     *    1、如果employee没有id,表示新增
     *    2.如果employee有id，表示修改
     * @param employee
     */
    void saveEmp(Employee employee);

    /**
     * 根据员工id查询员工明细
     * @param id
     * @return
     */
    Employee findEmpInfo(Integer id);

    /**
     * 删除员工信息
     * @param id
     */
    void delEmp(Integer id);

    void delEmp(Integer[] id);

    /**
     * 翻页查询员工列表
     * @param condition
     *    查询条件,1,2,3
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageModel<Employee> findEmpByPage(String condition, String keyword, int pageNo, int pageSize);
}
