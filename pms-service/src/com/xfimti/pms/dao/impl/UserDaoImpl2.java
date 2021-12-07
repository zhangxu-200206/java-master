package com.xfimti.pms.dao.impl;

import com.xfimti.pms.dao.UserDao;
import com.xfimti.pms.pojo.User;
import com.xfimti.pms.util.JdbcTemplate;
import com.xfimti.pms.util.RowMapper;

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
public class UserDaoImpl2 implements UserDao {

    //为了提交性能，将dao改成单例模式（饿汉式）
    private UserDaoImpl2(){}
    private static final UserDao instance = new UserDaoImpl2();
    public static UserDao getUserDao(){
        return instance;
    }

    @Override
    public void insert(User user) {
        JdbcTemplate.update("insert into tb_user values(null, ?,?,?,?,?,now())",
                new Object[]{
                        user.getUsername(),
                        user.getPassword(),
                        user.getRealName(),
                        user.getQuestion(),
                        user.getAnswer()
                });
    }

    @Override
    public void update(User user) {
        JdbcTemplate.update("update tb_user set password = ? where id = ?",
                new Object[]{
                        user.getPassword(),
                        user.getId()
                });
    }

    @Override
    public void delete(Integer id) {
        JdbcTemplate.update("delete from tb_user where id = ?",
                new Object[]{id});
    }

    @Override
    public User selectById(Integer id) {
        return JdbcTemplate.selectOne("select * from tb_user where id = ?", new Object[]{id}, new UserRowMapper());
    }

    @Override
    public User selectByUsername(String username) {
        return JdbcTemplate.selectOne("select * from tb_user where username = ?", new Object[]{username}, new UserRowMapper());
    }

    static class UserRowMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet rs) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRealName(rs.getString("realName"));
            user.setQuestion(rs.getString("question"));
            user.setAnswer(rs.getString("answer"));
            return user;
        }
    }


}
