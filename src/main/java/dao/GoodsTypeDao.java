package dao;

import domain.GoodsType;

import java.util.List;

public interface GoodsTypeDao {
    List<GoodsType> queryAllName();

    GoodsType queryById(Integer typeid);

    void insertGoodsType(String typename, GoodsType goodsType1);
}
