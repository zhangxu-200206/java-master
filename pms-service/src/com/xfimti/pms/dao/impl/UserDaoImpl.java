package com.xfimti.pms.dao.impl;

import com.xfimti.pms.dao.UserDao;
import com.xfimti.pms.exception.DataAccessException;
import com.xfimti.pms.pojo.User;
import com.xfimti.pms.util.ConnectionFactory;
import com.xfimti.pms.util.DbResourceCloseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * <pre>
 * 类的说明：
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-19 14:54
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public class UserDaoImpl implements UserDao {

    //为了提交性能，将dao改成单例模式（饿汉式）
    private UserDaoImpl(){}
    private static final UserDao instance = new UserDaoImpl();
    public static UserDao getUserDao(){
        return instance;
    }

    @Override
    public void insert(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "insert into tb_user values(null, ?,?,?,?,?,now())";
            //String sql = "insert into tb_user values(null, ?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            int index = 1;
            ps.setString(index++, user.getUsername());
            ps.setString(index++, user.getPassword());
            ps.setString(index++, user.getRealName());
            ps.setString(index++, user.getQuestion());
            ps.setString(index++, user.getAnswer());
            //为当前这条记录生成系统时间
            //ps.setTimestamp(index++, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();

        }catch (SQLException e){
            throw  new DataAccessException("插入管理员用户出错", e);
        }finally {
            DbResourceCloseUtils.close(ps, conn);
        }
    }

    @Override
    public void update(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "update tb_user set password = ? where id = ?";
            ps = conn.prepareStatement(sql);
            int index = 1;
            ps.setString(index++, user.getPassword());
            ps.setInt(index++, user.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            throw  new DataAccessException("修改管理员用户出错", e);
        }finally {
            DbResourceCloseUtils.close(ps, conn);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "delete from tb_user where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw  new DataAccessException("删除管理员用户出错", e);
        }finally {
            DbResourceCloseUtils.close(ps, conn);
        }
    }

    @Override
    public User selectById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "select * from tb_user where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRealName(rs.getString("realName"));
                user.setQuestion(rs.getString("question"));
                user.setAnswer(rs.getString("answer"));
            }
        }catch (SQLException e){
            throw  new DataAccessException("查询管理员用户出错", e);
        }finally {
            DbResourceCloseUtils.close(rs, ps, conn);
        }
        return user;
    }

    @Override
    public User selectByUsername(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "select * from tb_user where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRealName(rs.getString("realName"));
                user.setQuestion(rs.getString("question"));
                user.setAnswer(rs.getString("answer"));
            }
        }catch (SQLException e){
            throw  new DataAccessException("查询管理员用户出错", e);
        }finally {
            DbResourceCloseUtils.close(rs, ps, conn);
        }
        return user;
    }
}
