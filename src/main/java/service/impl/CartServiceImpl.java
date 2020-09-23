package service.impl;

import dao.CartDao;
import dao.GoodsDao;
import dao.impl.CartDaoImpl;
import dao.impl.GoodsDaoImpl;
import domain.Cart;
import domain.Goods;
import domain.User;
import service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    CartDao cartDao = new CartDaoImpl();
    GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public void addCart(User user, Goods goods,Integer number) {
        try {
            cartDao.addCart(user, goods,number);
        } catch (Exception e) {
            new RuntimeException(e);
        }
    }

    @Override
    public List<Cart> searchByUser(Integer userId) {
        try {
            List<Cart> cartList = cartDao.queryByUserId(userId);
            if (cartList!=null) {

                for (Cart cart : cartList) {
                    Goods goods = goodsDao.queryId(cart.getPid());
                    cart.setGoods(goods);
                }
                return cartList;
            }else {

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateCart(User user, Goods goods,Integer number,Integer price) {
        try {
            cartDao.addAjaxCart(user, goods,number,price);
        } catch (Exception e) {
            new RuntimeException(e);
        }
    }

    public void clearCart(User user){
        try {
            cartDao.deleteAllByUid(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
