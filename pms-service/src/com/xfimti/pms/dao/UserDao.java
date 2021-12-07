package com.xfimti.pms.dao;

import com.xfimti.pms.pojo.User;

/**
 * <pre>
 * 类的说明：
 *   对User进行CRUD操作
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-19 10:57
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public interface UserDao {
    /**
     * 插入用户信息
     * @param user
     */
    void insert(User user);

    /**
     * 修改用户信息
     * @param user
     */
    void update(User user);

    /**
     * 删除用户信息
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据用户id查询
     * @param id
     * @return
     */
    User selectById(Integer id);

    /**
     * 根据用户查询，因为username是不能有重复，所以最多只能返回一条数据
     * @param username
     * @return
     */
    User selectByUsername(String username);
}
