package dao.impl;

import dao.OrderDao;
import domain.Order;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DruidUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    QueryRunner qr = new QueryRunner(DruidUtils.getDateSource());
    @Override
    public int insertOrder(String oid,Integer aid, Integer uid, Integer money,String time) {

        String sql = "insert into tb_order(id,uid,money,status,time,aid) value(?,?,?,?,?,?);";
        Object[] params = {oid,uid,money,1,time,aid};
        try {
           return qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加订单失败！");
        }
    }

    @Override
    public List<Order> searchAllByUid(Integer uid) {
        String sql = "select* from tb_order where uid=?;";
        try {
            List<Order> orderList = qr.query(sql, new BeanListHandler<>(Order.class), uid);
            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException("查询所有订单失败！");
        }
    }

    @Override
    public Order searchById(String id) {
        String sql = "select * from tb_order where id=?;";
        try {
            Order order = qr.query(sql, new BeanHandler<>(Order.class), id);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException("根据订单id查询失败！");
        }
    }

    @Override
    public List<Order> serachAll() {
        String sql = "select * from tb_order;";
        List<Order> orderList = null;
        try {
            orderList = qr.query(sql, new BeanListHandler<>(Order.class));
        } catch (SQLException e) {
            throw new RuntimeException("查询所有订单失败！");
        }
        return orderList;
    }

    @Override
    public List<Order> searchByStatus(Integer status) {
        String sql = "select * from tb_order where status=?;";
        try {
            List<Order> orderList = qr.query(sql, new BeanListHandler<>(Order.class), status);
            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException("根据订单状态查询订单失败！");
        }
    }


}
