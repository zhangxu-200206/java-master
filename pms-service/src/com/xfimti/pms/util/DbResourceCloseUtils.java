package com.xfimti.pms.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <pre>
 * 类的说明：用来关闭数据资源
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-19 14:32
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public class DbResourceCloseUtils {
    public static void close(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement st){
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement st, Connection conn) {
        close(st);
        close(conn);
    }

    public static void close(ResultSet rs, Statement st, Connection conn) {
        close(rs);
        close(st, conn);
    }
}
