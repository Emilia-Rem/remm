package dao.impl;

import dao.GoodsDao;
import domain.Goods;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DruidUtils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    QueryRunner qr = new QueryRunner(DruidUtils.getDateSource());
    @Override
    public List<Goods> queryByTypeId(Integer typeid) {
        String sql = "select * from tb_goods where typeid=?;";
        try {
            List<Goods> goods = qr.query(sql, new BeanListHandler<>(Goods.class),typeid);
            return goods;
        } catch (SQLException e) {
            throw new RuntimeException("根据类别查询货物信息失败！");
        }
    }

    @Override
    public List<Goods> queryPageByTypeId(Integer typeid,Integer pageNum) {
        String sql = "select * from tb_goods where typeid=? limit ?,4";
        Object[] params = {typeid,(pageNum-1)*4};
        try {
            List<Goods> page = qr.query(sql, new BeanListHandler<>(Goods.class),params);
            return page;
        } catch (SQLException e) {
            throw new RuntimeException("分页查询失败！");
        }
    }

    @Override
    public Goods queryId(Integer id) {
        String sql = "select * from tb_goods where id=?;";
        try {
            Goods query = qr.query(sql, new BeanHandler<>(Goods.class), id);
            if(query!=null){
                return query;
            }else{
                throw new RuntimeException("查询结果为空！");
            }
        } catch (SQLException e) {
            throw new RuntimeException("根据ID查询失败！");
        }
    }

    @Override
    public List<Goods> queryAll() {
        String sql = "select * from tb_goods;";
        List<Goods> goodsList = null;
        try {
            goodsList = qr.query(sql, new BeanListHandler<>(Goods.class));
        } catch (SQLException e) {
            throw new RuntimeException("查询所有货物失败！");
        }
        return goodsList;
    }

    @Override
    public List<Goods> queryByName(String search) {
        String sql = "select * from tb_goods where name like?;";
        try {
            List<Goods> goodsList = qr.query(sql, new BeanListHandler<>(Goods.class), "%"+search+"%");
            return goodsList;
        } catch (SQLException e) {
            throw new RuntimeException("根据商品名模糊查询失败！");
        }
    }

    @Override
    public void insertGoods(Goods goods) {
        String sql = "insert into tb_goods(name,pubdate,picture,price,star,intro,typeid) value(?,?,?,?,?,?,?);";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(goods.getPubdate());
        Object[] params = {goods.getName(),format,goods.getPicture(),goods.getPrice(),goods.getStar(),goods.getIntro(),goods.getTypeid()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException("插入新商品失败！");
        }
    }
}
