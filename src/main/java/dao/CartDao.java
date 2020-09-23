package dao;

import domain.Cart;
import domain.Goods;
import domain.User;

import java.util.List;

public interface CartDao {
    void addCart(User user, Goods goods, Integer number);

    List<Cart> queryByUserId(Integer userId);

    void addAjaxCart(User user, Goods goods, Integer number, Integer price);

    void deleteAllByUid(User user);
}
