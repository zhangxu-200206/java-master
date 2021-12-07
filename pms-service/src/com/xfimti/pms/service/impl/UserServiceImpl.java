package com.xfimti.pms.service.impl;

import com.xfimti.pms.dao.UserDao;
import com.xfimti.pms.dao.impl.UserDaoImpl;
import com.xfimti.pms.pojo.User;
import com.xfimti.pms.service.LoginStatus;
import com.xfimti.pms.service.UserService;

/**
 * <pre>
 * 类的说明：
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-20 14:37
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public class UserServiceImpl implements UserService {
    //此处不完善，因为每产生一个service就会产生一个dao，那么将来提交一个请求，肯定会new service,导致new dao,导致jvm中很多service和dao对象
    //其实像dao,service这样线程安全的类，只需要一个对象就可以，能够完成方法调用。
    //所以从效率上讲应该考虑将dao和service开发成单例的。
    //UserDao userDao = new UserDaoImpl();
    UserDao userDao = UserDaoImpl.getUserDao();
    @Override
    public void register(User user) {
        userDao.insert(user);
    }

    @Override
    public boolean checkUserName(String username) {
        User user = userDao.selectByUsername(username);
        if(user!=null){
            //找到该用户名对应的用户，表示不可用
            return false;
        }else {
            //没有找到该用户名对应的用户，表示可用
            return true;
        }

    }

    @Override
    public LoginStatus login(String username, String password) {
        User user = userDao.selectByUsername(username);
        if(user==null){
            return LoginStatus.USER_NAME_NOT_EXIST;
        }else{
            if(user.getPassword().equals(password)){
                return LoginStatus.SUCCESS;
            }
        }
        return LoginStatus.USER_PASSWORD_ERROR;
    }

    @Override
    public User findUserPassword(String username, String question, String answer) {
        User user = userDao.selectByUsername(username);
        if(user!=null){
            if(user.getQuestion().equals(question) && user.getAnswer().equals(answer)){
                return user;
            }
        }
        return null;
    }
}
