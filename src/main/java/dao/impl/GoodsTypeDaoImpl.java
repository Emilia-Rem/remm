package dao.impl;

import dao.GoodsTypeDao;
import domain.GoodsType;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DruidUtils;

import java.sql.SQLException;
import java.util.List;

public class GoodsTypeDaoImpl implements GoodsTypeDao {
    QueryRunner qr = new QueryRunner(DruidUtils.getDateSource());
    @Override
    public List<GoodsType> queryAllName() {
        String sql = "select * from tb_goods_type";
        List<GoodsType> types = null;
        try {
            types = qr.query(sql, new BeanListHandler<>(GoodsType.class));
        } catch (SQLException e) {
            throw new RuntimeException("查询商品类型失败！");
        }
        return types;
    }

    @Override
    public GoodsType queryById(Integer typeid) {
        String sql = "select * from tb_goods_type where id=?;";
        try {
            GoodsType query = qr.query(sql, new BeanHandler<>(GoodsType.class), typeid);
            if(query!=null){
                return query;
            } else{
                throw new RuntimeException("查找结果为空！");
            }
        } catch (SQLException e) {
            throw new RuntimeException("根据ID查找类别失败");
        }
    }

    @Override
    public void insertGoodsType(String typename, GoodsType goodsType1) {
        String sql = "insert into tb_goods_type(name,level,parent) value(?,?,?);";
        Object[] params = {typename,goodsType1.getLevel()+1,goodsType1.getId()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException("插入新商品类型失败！");
        }
    }
}
