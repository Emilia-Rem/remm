package service.impl;

import dao.AddressDao;
import dao.OrderDao;
import dao.UserDao;
import dao.impl.AddressDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.UserDaoImpl;
import domain.Order;
import domain.User;
import service.OrderService;
import utils.OrderIdUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    UserDao userDao = new UserDaoImpl();
    OrderDao orderDao = new OrderDaoImpl();
    AddressDao addressDao = new AddressDaoImpl();
    @Override
    public Order addOrderByAid(Integer aid, Integer uid, Integer money) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        String oid = OrderIdUtils.getOrderId();
        try {
            orderDao.insertOrder(oid,aid,uid,money,time);
            Order order = orderDao.searchById(oid);
            return order;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getOrderAllByUid(Integer uid) {
        try {
            List<Order> orderList = orderDao.searchAllByUid(uid);
            for (Order order : orderList) {
                order.setAddress(addressDao.queryByAid(order.getAid()));
            }
            return orderList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order searchOrderById(String oid) {
        Order order = null;
        try {
            order = orderDao.searchById(oid);
            order.setAddress(addressDao.queryByAid(order.getAid()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    @Override
    public List<Order> getOrderAll() {
        List<Order> orderList = null;
        try {
            orderList = orderDao.serachAll();
            for (Order order : orderList) {
                User user = userDao.queryById(order.getUid());
                order.setUsername(user.getUsername());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }

    @Override
    public List<Order> searchOrderByUNameAndStatus(String username, Integer status) {
        User user = userDao.queryByName(username);
        List<Order> orderList  = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        if(status==0&&user!=null){
            orderList = orderDao.searchAllByUid(user.getId());
            for (Order order : orderList) {
                order.setUsername(user.getUsername());
            }
            return orderList;
        }else if(user==null&&status!=0){
            orderList = orderDao.searchByStatus(status);
            for (Order order : orderList) {
                User user1 = userDao.queryById(order.getUid());
                order.setUsername(user1.getUsername());
            }
            return orderList;
        }else if(user!=null&&status!=0){
            orderList = orderDao.searchByStatus(status);
            for (Order order : orderList) {
                if(order.getUid()==user.getId()){
                    order.setUsername(user.getUsername());
                    orders.add(order);
                }
            }
            return orders;
        }else{
            OrderService orderService = new OrderServiceImpl();
            return orderService.getOrderAll();
        }
    }
}
