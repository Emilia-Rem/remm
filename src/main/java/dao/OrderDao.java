package dao;

import domain.Order;

import java.util.List;

public interface OrderDao {
    public int insertOrder(String oid, Integer aid, Integer uid, Integer money, String time);

    List<Order> searchAllByUid(Integer uid);

    Order searchById(String id);

    List<Order> serachAll();

    List<Order> searchByStatus(Integer status);
}
