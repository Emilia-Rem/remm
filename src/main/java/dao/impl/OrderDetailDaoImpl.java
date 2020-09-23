package dao.impl;

import dao.OrderDetailDao;
import domain.OrderDetail;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DruidUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    QueryRunner qr = new QueryRunner(DruidUtils.getDateSource());
    @Override
    public void insertOrderDetail(String oid, Integer gid, Integer num, Integer money) {
        String sql = "insert into tb_orderdetail(oid,pid,num,money) value(?,?,?,?);";
        Object[] params = {oid,gid,num,money};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException("插入订单详情失败！");
        }
    }

    @Override
    public List<OrderDetail> searchByOid(String oid) {
        String sql = "select * from tb_orderdetail where oid=?";
        try {
            List<OrderDetail> detailList = qr.query(sql, new BeanListHandler<>(OrderDetail.class), oid);
            return detailList;
        } catch (SQLException e) {
            throw new RuntimeException("根据oid查询订单详情失败！");
        }
    }


}
