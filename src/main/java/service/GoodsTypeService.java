package service;

import domain.GoodsType;

import java.util.List;

public interface GoodsTypeService {
    List<GoodsType> AllTypes();
    GoodsType getTypesById(Integer typeid);

    void addGoodsType(String typename, GoodsType goodsType1);
}
