package com.xfimti.pms.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <pre>
 * 类的说明：
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-25 10:55
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public interface RowMapper <T>{
    T mapRow(ResultSet rs) throws SQLException;
}
