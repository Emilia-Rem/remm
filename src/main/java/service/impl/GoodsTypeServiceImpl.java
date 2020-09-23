package service.impl;

import dao.GoodsTypeDao;
import dao.impl.GoodsTypeDaoImpl;
import domain.GoodsType;
import service.GoodsTypeService;

import java.util.List;

public class GoodsTypeServiceImpl implements GoodsTypeService {
    GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
    @Override
    public List<GoodsType> AllTypes() {
        List<GoodsType> goodsTypes = null;
        try {
            goodsTypes = goodsTypeDao.queryAllName();
        } catch (Exception e) {
            new RuntimeException(e);
        }
        return goodsTypes;
    }

    public GoodsType getTypesById(Integer typeid) {
        GoodsType goodsTypes = null;
        try {
            goodsTypes = goodsTypeDao.queryById(typeid);
            return goodsTypes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addGoodsType(String typename, GoodsType goodsType1) {
        try {
            goodsTypeDao.insertGoodsType(typename,goodsType1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
