package service;

import domain.Cart;
import domain.Goods;
import domain.User;

import java.util.List;

public interface CartService {
    //添加购物车
    void addCart(User user, Goods goods, Integer number);
    //查找登录用户的购物车
    List<Cart> searchByUser(Integer userId);
    //更新购物车
    void updateCart(User user, Goods goods, Integer number, Integer price);
    //清空用户购物车
    void clearCart(User user);

}
