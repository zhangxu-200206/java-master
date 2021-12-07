package com.xfimti.pms.dao;

import com.xfimti.pms.pojo.Employee;

import java.sql.Date;
import java.util.List;

/**
 * <pre>
 * 类的说明：
 *     EmployeeDao进行CRUD操作
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-19 10:57
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public interface EmployeeDao {
    /**
     * 插入新员工
     * @param employee
     */
    void insert(Employee employee);

    /**
     * 修改员工信息
     * @param employee
     */
    void update(Employee employee);

    /**
     * 删除员工信息
     * @param id
     */
    void delete(Integer id);

    void delete(Integer[] ids);

    /**
     * 根据员工ID查询员工信息
     * @param id
     * @return
     */
    Employee selectById(Integer id);

    /**
     * 翻页查询所有员工
     * @param pageNo
     *      页码
     * @param pageSize
     *      每页显示的记录数
     * @return
     */
    List<Employee> selectList(int pageNo, int pageSize);

    /**
     * 根据员工姓名翻页查询
     * @param empName
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Employee> selectListByName(String empName, int pageNo, int pageSize);

    /**
     * 根据员工性别翻页查询
     * @param gender
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Employee> selectListByGender(String gender, int pageNo, int pageSize);

    /**
     * 根据员工性别翻页查询
     * @param hiredate
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Employee> selectListByHiredate(Date hiredate, int pageNo, int pageSize);

    /**
     * 统计所有数据
     * @return
     */
    int count();

    /**
     * 根据名字统计
     * @param name
     * @return
     */
    int countByName(String name);

    int countByGender(String gender);

    int countByHiredate(Date hiredate);
}
