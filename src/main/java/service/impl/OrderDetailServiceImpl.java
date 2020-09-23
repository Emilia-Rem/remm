package service.impl;

import dao.CartDao;
import dao.GoodsDao;
import dao.OrderDetailDao;
import dao.impl.CartDaoImpl;
import dao.impl.GoodsDaoImpl;
import dao.impl.OrderDetailDaoImpl;
import domain.Cart;
import domain.Goods;
import domain.OrderDetail;
import domain.OrderWrapper;
import service.OrderDetailService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {
    GoodsDao goodsDao = new GoodsDaoImpl();
    CartDao cartDao = new CartDaoImpl();
    OrderDetailDao detailDao = new OrderDetailDaoImpl();
    @Override
    public void addOrderDetail(String oid, Integer uid) {
        List<Cart> cartList = cartDao.queryByUserId(uid);
        for (Cart cart : cartList) {
            try {
                detailDao.insertOrderDetail(oid, cart.getPid(),cart.getNum() ,cart.getMoney() );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Goods> getGoodsByGid(String oid) {
        try {
            List<Goods> goodsList = new LinkedList<>();
            List<OrderDetail> orderDetails = detailDao.searchByOid(oid);
            for (OrderDetail orderDetail : orderDetails) {
                Goods goods = goodsDao.queryId(orderDetail.getPid());
                goodsList.add(goods);
            }
            return goodsList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderWrapper> getOrderWrapper(String oid) {
        OrderWrapper orderWrapper;
        List<OrderWrapper> orderWrapperList = new ArrayList<>();
        List<OrderDetail> orderDetails = detailDao.searchByOid(oid);
        for (OrderDetail orderDetail : orderDetails) {
            Goods goods = goodsDao.queryId(orderDetail.getPid());
            orderWrapper = new OrderWrapper(goods.getName(),goods.getPubdate(),goods.getPicture() , goods.getPrice(), goods.getStar(), orderDetail.getNum(), orderDetail.getMoney());
            orderWrapperList.add(orderWrapper);
        }
        return orderWrapperList;
    }

}
