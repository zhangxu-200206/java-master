package com.xfimti.pms.pojo;

import java.sql.Date;

/**
 * <pre>
 * 类的说明：
 *   员工类
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-19 10:51
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public class Employee {
    private Integer id;
    private String empName;
    //员工性别 1:代表男，2代表女
    private String gender;
    private Date birthday;
    private Double salary;
    private Date hiredate;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                ", hiredate=" + hiredate +
                '}';
    }
}
