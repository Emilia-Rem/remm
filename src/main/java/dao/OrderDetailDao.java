package dao;

import domain.OrderDetail;

import java.util.List;

public interface OrderDetailDao {
    void insertOrderDetail(String oid, Integer gid, Integer num, Integer money);

    List<OrderDetail> searchByOid(String oid);

}
