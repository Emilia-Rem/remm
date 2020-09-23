package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
 * wgy
 * 2020/8/31
 * 9:35
 */
public class DruidUtils {

    private static DruidDataSource dataSource=null;
    private static ThreadLocal<Connection> threadLocal=new ThreadLocal<>();
    static {
        //初始化dataSource
        try {
            Properties properties=new Properties();
            InputStream is = DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);
            is.close();
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("初始化连接池shibai");
        }
    }

    public static DataSource getDateSource(){
        return dataSource;
    }

    public static Connection getConnection(){
        try {
            Connection conn = threadLocal.get();
            if(conn==null){
                conn=dataSource.getConnection();
                threadLocal.set(conn);
            }
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeAll(ResultSet rs, Statement stat,Connection conn){
        try {
            if(rs!=null){
                rs.close();
            }
            if(stat!=null){
                stat.close();
            }
            if(conn!=null){
                if(conn.getAutoCommit()) {
                    threadLocal.remove();
                    conn.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //事务有关的方法
    public static void beginTransaction() throws SQLException{
        Connection conn=getConnection();
        if(conn!=null){
            conn.setAutoCommit(false);
        }
    }
    public static void commit() throws SQLException{
        Connection conn=getConnection();
        if(conn!=null){
            conn.commit();
        }
    }

    public static void rollback() throws SQLException{
        Connection conn=getConnection();
        if(conn!=null){
            conn.rollback();
        }
    }

    public static void close() throws SQLException{
        Connection conn=getConnection();
        if(conn!=null){
            threadLocal.remove();
            conn.close();
        }
    }
}
