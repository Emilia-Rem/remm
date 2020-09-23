package service;

import domain.Goods;
import domain.OrderWrapper;

import java.util.List;

public interface OrderDetailService {
    void addOrderDetail(String oid, Integer uid);

    List<Goods> getGoodsByGid(String oid);

    List<OrderWrapper> getOrderWrapper(String oid);
}
