package com.xfimti.pms.util;

import com.xfimti.pms.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 类的说明：
 * 联系我们： Hery138@163.com
 * 创建时间： 2021-10-25 10:44
 * 版权说明： 襄阳英泰移动通信学院2006-2021
 * </pre>
 */
public class JdbcTemplate {
    public static int update(String sql, Object[] params){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql);
            if(params!=null){
                for (int i = 0; i < params.length; i++) {
                    Object param = params[i];
                    if(param.getClass() == int.class || param.getClass()==Integer.class){
                        ps.setInt(i+1, (Integer)param);
                    } else if(param.getClass() == double.class || param.getClass()==Double.class){
                        ps.setDouble(i+1, (Double)param);
                    } else if(param.getClass() == float.class || param.getClass()==Float.class){
                        ps.setFloat(i+1, (Float)param);
                    } else if(param.getClass() == String.class){
                        ps.setString(i+1, (String)param);
                    } else if(param.getClass() == Date.class){
                        ps.setDate(i+1, (Date)param);
                    } else{
                        ps.setObject(i+1, param);
                    }
                }
            }
            return ps.executeUpdate();
        }catch (SQLException e){
            throw  new DataAccessException("更新数据出错", e);
        }finally {
            DbResourceCloseUtils.close(ps, conn);
        }
    }

    public static <T> T selectOne(String sql, Object[] params,RowMapper<T> rowMapper){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        T t = null;
        try{
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql);
            if(params!=null){
                for (int i = 0; i < params.length; i++) {
                    Object param = params[i];
                    if(param.getClass() == int.class || param.getClass()==Integer.class){
                        ps.setInt(i+1, (Integer)param);
                    } else if(param.getClass() == double.class || param.getClass()==Double.class){
                        ps.setDouble(i+1, (Double)param);
                    } else if(param.getClass() == float.class || param.getClass()==Float.class){
                        ps.setFloat(i+1, (Float)param);
                    } else if(param.getClass() == String.class){
                        ps.setString(i+1, (String)param);
                    } else if(param.getClass() == Date.class){
                        ps.setDate(i+1, (Date)param);
                    } else{
                        ps.setObject(i+1, param);
                    }
                }
            }
            rs = ps.executeQuery();
            if(rs.next()){
                t = rowMapper.mapRow(rs);
            }
        }catch (SQLException e){
            throw  new DataAccessException("查询数据出错", e);
        }finally {
            DbResourceCloseUtils.close(rs, ps, conn);
        }
        return t;
    }

    public static <T> List<T> selectList(String sql, Object[] params,RowMapper<T> rowMapper){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();
        try{
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql);
            if(params!=null){
                for (int i = 0; i < params.length; i++) {
                    Object param = params[i];
                    if(param.getClass() == int.class || param.getClass()==Integer.class){
                        ps.setInt(i+1, (Integer)param);
                    } else if(param.getClass() == double.class || param.getClass()==Double.class){
                        ps.setDouble(i+1, (Double)param);
                    } else if(param.getClass() == float.class || param.getClass()==Float.class){
                        ps.setDouble(i+1, (Float)param);
                    } else if(param.getClass() == String.class){
                        ps.setString(i+1, (String)param);
                    } else if(param.getClass() == Date.class){
                        ps.setDate(i+1, (Date)param);
                    } else{
                        ps.setObject(i+1, param);
                    }
                }
            }
            rs = ps.executeQuery();
            while(rs.next()){
               T t = rowMapper.mapRow(rs);
               list.add(t);
            }
        }catch (SQLException e){
            throw  new DataAccessException("查询数据出错", e);
        }finally {
            DbResourceCloseUtils.close(rs, ps, conn);
        }
        return list;
    }

    public static int count(String sql, Object[] params){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;
        try{
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql);
            if(params!=null){
                for (int i = 0; i < params.length; i++) {
                    Object param = params[i];
                    if(param.getClass() == int.class || param.getClass()==Integer.class){
                        ps.setInt(i+1, (Integer)param);
                    } else if(param.getClass() == double.class || param.getClass()==Double.class){
                        ps.setDouble(i+1, (Double)param);
                    } else if(param.getClass() == float.class || param.getClass()==Float.class){
                        ps.setDouble(i+1, (Float)param);
                    } else if(param.getClass() == String.class){
                        ps.setString(i+1, (String)param);
                    } else if(param.getClass() == Date.class){
                        ps.setDate(i+1, (Date)param);
                    } else{
                        ps.setObject(i+1, param);
                    }
                }
            }
            rs = ps.executeQuery();
            if (rs.next()){
                total =  rs.getInt(1);
            }
        }catch (SQLException e){
            throw  new DataAccessException("组函数执行出错", e);
        }finally {
            DbResourceCloseUtils.close(rs, ps, conn);
        }
        return total;
    }
}
