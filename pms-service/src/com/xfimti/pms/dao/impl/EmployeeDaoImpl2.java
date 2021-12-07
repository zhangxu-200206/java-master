package com.xfimti.pms.dao.impl;

import com.xfimti.pms.dao.EmployeeDao;
import com.xfimti.pms.pojo.Employee;
import com.xfimti.pms.util.JdbcTemplate;
import com.xfimti.pms.util.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * <pre>
 * 类的说明：
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-19 15:33
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public class EmployeeDaoImpl2 implements EmployeeDao {
    @Override
    public void insert(Employee employee) {
        JdbcTemplate.update("insert into tb_emp values(null, ?,?,?,?,?)",
                            new Object[]{employee.getEmpName(),
                                         employee.getGender(),
                                         employee.getBirthday(),
                                         employee.getSalary(),
                                         employee.getHiredate()});
    }

    @Override
    public void update(Employee employee) {
        JdbcTemplate.update("update tb_emp set empName = ?, gender=?, birthday=?, salary=?, hiredate=? where id = ?",
                new Object[]{
                        employee.getEmpName(),
                        employee.getGender(),
                        employee.getBirthday(),
                        employee.getBirthday(),
                        employee.getBirthday(),
                        employee.getHiredate(),
                        employee.getId()
        });

    }

    //删除    物理删除  逻辑删除
    @Override
    public void delete(Integer id) {
        JdbcTemplate.update("delete from tb_emp  where id = ?",
                new Object[]{id});
    }

    @Override
    public Employee selectById(Integer id) {
        return JdbcTemplate.selectOne("select * from tb_emp where id = ?",
                new Object[]{id},new EmployeeRowMapper());
    }


    @Override
    public List<Employee> selectList(int pageNo, int pageSize) {
       return  JdbcTemplate.selectList("select * from tb_emp  limit ?,?",
                                        new Object[]{(pageNo - 1) * pageSize, pageSize},
                                        new EmployeeRowMapper());
    }

    @Override
    public List<Employee> selectListByName(String empName, int pageNo, int pageSize) {
        return  JdbcTemplate.selectList("select * from tb_emp where empName like ?  limit ?,?",
                    new Object[]{"%"+empName+"%",(pageNo - 1) * pageSize, pageSize},
                    new EmployeeRowMapper());

    }

    @Override
    public List<Employee> selectListByGender(String gender, int pageNo, int pageSize) {
        return  JdbcTemplate.selectList("select * from tb_emp where gender = ?  limit ?,?",
                    new Object[]{gender,(pageNo - 1) * pageSize, pageSize},
                    new EmployeeRowMapper());
    }

    @Override
    public List<Employee> selectListByHiredate(Date hiredate, int pageNo, int pageSize) {
        return  JdbcTemplate.selectList("select * from tb_emp where hiredate = ?  limit ?,?",
                    new Object[]{hiredate,(pageNo - 1) * pageSize, pageSize},
                    new EmployeeRowMapper());
    }

    @Override
    public int count() {
        return JdbcTemplate.count("select count(*) from tb_emp", null);
    }

    @Override
    public int countByName(String name) {
        return JdbcTemplate.count("select count(*) from tb_emp where empName like ?", new Object[]{"%"+name+"%"});
    }

    @Override
    public int countByGender(String gender) {
        return JdbcTemplate.count("select count(*) from tb_emp where gender = ?", new Object[]{gender});

    }

    @Override
    public int countByHiredate(Date hiredate) {
        return JdbcTemplate.count("select count(*) from tb_emp where hiredate = ?", new Object[]{hiredate});

    }

    static class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs) throws SQLException {
            Employee emp = new Employee();
            emp.setId(rs.getInt("id"));
            emp.setEmpName(rs.getString("empName"));
            emp.setGender(rs.getString("gender"));
            emp.setBirthday(rs.getDate("birthday"));
            emp.setSalary(rs.getDouble("salary"));
            emp.setHiredate(rs.getDate("hiredate"));
            return emp;
        }
    }

}
