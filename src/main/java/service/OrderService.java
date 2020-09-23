package service;

import domain.Order;

import java.util.List;

public interface OrderService {
    Order addOrderByAid(Integer aid, Integer uid, Integer money);

    List<Order> getOrderAllByUid(Integer uid);

    Order searchOrderById(String oid);

    List<Order> getOrderAll();

    List<Order> searchOrderByUNameAndStatus(String username, Integer status);
}
