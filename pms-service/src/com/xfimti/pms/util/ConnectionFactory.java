package com.xfimti.pms.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <pre>
 * 类的说明：
 *    数据连接工厂工具类，专门用来获得数据的连接
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-19 14:24
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public class ConnectionFactory {
    private static final String DB_CONFIG_FILE = "db.properties";
    private static Properties config = new Properties();
    static{
        //读取配置文件
        InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE);//到项目classpath路径下去加载配置文件，也就src目录
        if(in == null){
            throw new RuntimeException("在classpath目录下没有找到"+DB_CONFIG_FILE);
        }

        try {
            config.load(in);
        } catch (IOException e) {
            throw new RuntimeException(DB_CONFIG_FILE+"文件出错", e);
        }

        //1.加载驱动
        try {
            Class.forName(config.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("驱动程序有误", e);
        }
    }

    /**
     * 静态工具方法，方便获得数据连接
     * @return
     */
    public static Connection getConnection(){
        //2.创建连接
        Connection conn = null;
        try {
            conn = DriverManager.getConnection( config.getProperty("jdbc.url"),
                    config.getProperty("jdbc.username"),
                    config.getProperty("jdbc.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //3.创建statement
        return conn;
    }
}
