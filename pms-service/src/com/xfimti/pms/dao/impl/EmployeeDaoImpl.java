package com.xfimti.pms.dao.impl;

import com.xfimti.pms.dao.EmployeeDao;
import com.xfimti.pms.exception.DataAccessException;
import com.xfimti.pms.pojo.Employee;
import com.xfimti.pms.util.ConnectionFactory;
import com.xfimti.pms.util.DbResourceCloseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 类的说明：
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-19 15:33
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public void insert(Employee employee) {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "insert into tb_emp values(null, ?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            int index = 1;
            ps.setString(index++, employee.getEmpName());
            ps.setString(index++, employee.getGender());
            ps.setDate(index++, employee.getBirthday());
            ps.setDouble(index++, employee.getSalary());
            ps.setDate(index++, employee.getHiredate());

            ps.executeUpdate();

        }catch (SQLException e){
            throw  new DataAccessException("插入员工信息出错", e);
        }finally {
            DbResourceCloseUtils.close(ps, conn);
        }
    }

    @Override
    public void update(Employee employee) {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "update tb_emp set empName = ?, gender=?, birthday=?, salary=?, hiredate=? where id = ?";
            ps = conn.prepareStatement(sql);
            int index = 1;
            ps.setString(index++, employee.getEmpName());
            ps.setString(index++, employee.getGender());
            ps.setDate(index++, employee.getBirthday());
            ps.setDouble(index++, employee.getSalary());
            ps.setDate(index++, employee.getHiredate());
            ps.setInt(index++, employee.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            throw  new DataAccessException("修改员工信息出错", e);
        }finally {
            DbResourceCloseUtils.close(ps, conn);
        }
    }

    //删除    物理删除  逻辑删除
    @Override
    public void delete(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "delete from tb_emp  where id = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw  new DataAccessException("删除员工信息出错", e);
        }finally {
            DbResourceCloseUtils.close(ps, conn);
        }
    }

    @Override
    public void delete(Integer[] ids) {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = ConnectionFactory.getConnection();
            StringBuffer sql = new StringBuffer("delete from tb_emp  where 1=2 ");
            if(ids != null && ids.length>0){
                sql.append(" or id in ( ");
                for (Integer id : ids) {
                    sql.append("?").append(" ,");
                }
                if(sql.lastIndexOf(",")!=-1){
                    sql.deleteCharAt(sql.lastIndexOf(","));
                }
                sql.append(" )");
            }
            //delete from tb_emp where 1=2 or id in(?,?,?);
            //delete from tb_emp where id=1 or id = 2 or id = 3
            System.out.println(sql.toString());
            ps = conn.prepareStatement(sql.toString());
            if(ids != null && ids.length>0){
                for (int i = 0; i < ids.length; i++) {
                    ps.setInt(i+1, ids[i]);
                }
            }
            ps.executeUpdate();
        }catch (SQLException e){
            throw  new DataAccessException("删除员工信息出错", e);
        }finally {
            DbResourceCloseUtils.close(ps, conn);
        }
    }


    @Override
    public Employee selectById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee emp = null;
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "select * from tb_emp where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setEmpName(rs.getString("empName"));
                emp.setGender(rs.getString("gender"));
                emp.setBirthday(rs.getDate("birthday"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setHiredate(rs.getDate("hiredate"));
            }
        }catch (SQLException e){
            throw  new DataAccessException("查询员工信息出错", e);
        }finally {
            DbResourceCloseUtils.close(rs, ps, conn);
        }
        return emp;
    }


    @Override
    public List<Employee> selectList(int pageNo, int pageSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Employee> empList = new ArrayList<>();
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "select * from tb_emp  limit ?,?";;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (pageNo-1)*pageSize);
            ps.setInt(2, pageSize);

            rs = ps.executeQuery();
            while (rs.next()){
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setEmpName(rs.getString("empName"));
                emp.setGender(rs.getString("gender"));
                emp.setBirthday(rs.getDate("birthday"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setHiredate(rs.getDate("hiredate"));
                empList.add(emp);
            }
        }catch (SQLException e){
            throw  new DataAccessException("查询员工信息出错", e);
        }finally {
            DbResourceCloseUtils.close(rs, ps, conn);
        }
        return empList;

    }

    @Override
    public List<Employee> selectListByName(String empName, int pageNo, int pageSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Employee> empList = new ArrayList<>();
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "select * from tb_emp where empName like ?  limit ?,?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, "%"+empName+"%");
            ps.setInt(2, (pageNo-1)*pageSize);
            ps.setInt(3, pageSize);

            rs = ps.executeQuery();
            while (rs.next()){
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setEmpName(rs.getString("empName"));
                emp.setGender(rs.getString("gender"));
                emp.setBirthday(rs.getDate("birthday"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setHiredate(rs.getDate("hiredate"));
                empList.add(emp);
            }
        }catch (SQLException e){
            throw  new DataAccessException("查询员工信息出错", e);
        }finally {
            DbResourceCloseUtils.close(rs, ps, conn);
        }
        return empList;
    }

    @Override
    public List<Employee> selectListByGender(String gender, int pageNo, int pageSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Employee> empList = new ArrayList<>();
        try{
            conn = ConnectionFactory.getConnection();
            String sql =  "select * from tb_emp where gender = ?  limit ?,?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, gender);
            ps.setInt(2, (pageNo-1)*pageSize);
            ps.setInt(3, pageSize);

            rs = ps.executeQuery();
            while (rs.next()){
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setEmpName(rs.getString("empName"));
                emp.setGender(rs.getString("gender"));
                emp.setBirthday(rs.getDate("birthday"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setHiredate(rs.getDate("hiredate"));
                empList.add(emp);
            }
        }catch (SQLException e){
            throw  new DataAccessException("查询员工信息出错", e);
        }finally {
            DbResourceCloseUtils.close(rs, ps, conn);
        }
        return empList;
    }

    @Override
    public List<Employee> selectListByHiredate(Date hiredate, int pageNo, int pageSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Employee> empList = new ArrayList<>();
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "select * from tb_emp where hiredate = ?  limit ?,?";
            ps = conn.prepareStatement(sql);

            ps.setDate(1, hiredate);
            ps.setInt(2, (pageNo-1)*pageSize);
            ps.setInt(3, pageSize);

            rs = ps.executeQuery();
            while (rs.next()){
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setEmpName(rs.getString("empName"));
                emp.setGender(rs.getString("gender"));
                emp.setBirthday(rs.getDate("birthday"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setHiredate(rs.getDate("hiredate"));
                empList.add(emp);
            }
        }catch (SQLException e){
            throw  new DataAccessException("查询员工信息出错", e);
        }finally {
            DbResourceCloseUtils.close(rs, ps, conn);
        }
        return empList;
    }

    @Override
    public int count() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;
        try{
            conn = ConnectionFactory.getConnection();
            String sql =  "select count(*) from tb_emp";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()){
                total =  rs.getInt(1);
            }
        }catch (SQLException e){
            throw  new DataAccessException("查询员工数量", e);
        }finally {
            DbResourceCloseUtils.close(rs, ps, conn);
        }
        return total;
    }

    @Override
    public int countByName(String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;
        try{
            conn = ConnectionFactory.getConnection();
            String sql =  "select count(*) from tb_emp where empName like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+name+"%");
            rs = ps.executeQuery();
            if (rs.next()){
                total =  rs.getInt(1);
            }
        }catch (SQLException e){
            throw  new DataAccessException("查询员工数量", e);
        }finally {
            DbResourceCloseUtils.close(rs, ps, conn);
        }
        return total;
    }

    @Override
    public int countByGender(String gender) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;
        try{
            conn = ConnectionFactory.getConnection();
            String sql =  "select count(*) from tb_emp where gender = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, gender);
            rs = ps.executeQuery();
            if (rs.next()){
                total =  rs.getInt(1);
            }
        }catch (SQLException e){
            throw  new DataAccessException("查询员工数量", e);
        }finally {
            DbResourceCloseUtils.close(rs, ps, conn);
        }
        return total;
    }

    @Override
    public int countByHiredate(Date hiredate) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;
        try{
            conn = ConnectionFactory.getConnection();
            String sql =  "select count(*) from tb_emp where hiredate = ?";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, hiredate);
            rs = ps.executeQuery();
            if (rs.next()){
                total =  rs.getInt(1);
            }
        }catch (SQLException e){
            throw  new DataAccessException("查询员工数量", e);
        }finally {
            DbResourceCloseUtils.close(rs, ps, conn);
        }
        return total;
    }


/*    private List<Employee> selectListByCondition(String condition, String keyword, int pageNo, int pageSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Employee> empList = new ArrayList<>();
        try{
            conn = ConnectionFactory.getConnection();
            String sql = null;
            if(condition.equals("1")){
                sql = "select * from tb_emp  limit ?,?";
            }else if(condition.equals("2")){
                sql = "select * from tb_emp where empName like ?  limit ?,?";
            }else if(condition.equals("3")){
                sql = "select * from tb_emp where gender = ?  limit ?,?";
            }else if(condition.equals("4")){
                sql = "select * from tb_emp where hiredate = ?  limit ?,?";
            }else{
                throw new IllegalArgumentException("查询条件condition错误,请使用1,2,3,4");
            }

            //String sql = "select * from tb_emp where empName like ?  limit ?,?";
            //String sql = "select * from tb_emp where gender = ?  limit ?,?";
            //String sql = "select * from tb_emp where hiredate = ?  limit ?,?";
            ps = conn.prepareStatement(sql);
            if(condition.equals("1")){
                ps.setInt(1, (pageNo-1)*pageSize);
                ps.setInt(2, pageSize);
            }else if(condition.equals("2")){
                ps.setString(1, "%"+keyword+"%");
                ps.setInt(2, (pageNo-1)*pageSize);
                ps.setInt(3, pageSize);
            }else if(condition.equals("3")){
                ps.setString(1, keyword);
                ps.setInt(2, (pageNo-1)*pageSize);
                ps.setInt(3, pageSize);
            }else if(condition.equals("4")){
                ps.setDate(1, Date.valueOf(keyword));
                ps.setInt(2, (pageNo-1)*pageSize);
                ps.setInt(3, pageSize);
            }else{
                throw new IllegalArgumentException("查询条件condition错误,请使用1,2,3,4");
            }
            rs = ps.executeQuery();
            while (rs.next()){
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setEmpName(rs.getString("empName"));
                emp.setGender(rs.getString("gender"));
                emp.setBirthday(rs.getDate("birthday"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setHiredate(rs.getDate("hiredate"));
                empList.add(emp);
            }
        }catch (SQLException e){
            throw  new DataAccessException("查询员工信息出错", e);
        }finally {
            DbResourceCloseUtils.close(rs, ps, conn);
        }
        return empList;
    }*/
}
