package dao.impl;

import dao.CartDao;
import domain.Cart;
import domain.Goods;
import domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DruidUtils;

import java.sql.SQLException;
import java.util.List;

public class CartDaoImpl implements CartDao {
    QueryRunner qr = new QueryRunner(DruidUtils.getDateSource());
    @Override
    public void addCart(User user, Goods goods, Integer number) {
        String sql = "insert into tb_cart(id,pid,num,money) value(?,?,?,?);";
        Object[] params = {user.getId(),goods.getId(),number,goods.getPrice()};
        try {
            int update = qr.update(sql, params);
            System.out.println(update);
            if(update==0){
                throw new RuntimeException("购物车添加失败！");
            }
        } catch (SQLException e) {
            throw new RuntimeException("购物车添加失败！");
        }

    }

    /**
     * 根据用户Id查询购物车
     * @param userId
     * @return
     */
    @Override
    public List<Cart> queryByUserId(Integer userId) {
        String sql = "select * from tb_cart where id=?;";
        try {
            List<Cart> cartList = qr.query(sql, new BeanListHandler<>(Cart.class), userId);
            return cartList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("根据用户名查询购物车失败",e);

        }
    }

    public void addAjaxCart(User user, Goods goods, Integer number, Integer price) {
        String sql = "update tb_cart set num=?,money=? where id=?&&pid=?;";
        Object[] params = {number,price,user.getId(),goods.getId()};
        try {
            int update = qr.update(sql, params);
            if(update==0){
                throw new RuntimeException("购物车更新失败！");
            }
        } catch (SQLException e) {
            throw new RuntimeException("购物车更新失败！");
        }

    }

    public void deleteAllByUid(User user){
        String sql = "delete from tb_cart where id=?;";
        try {
            int update = qr.update(sql, user.getId());
            if(update==0){
                throw new RuntimeException("根据用户id清空失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException("清空cart表失败");
        }
    }
}
