package service.impl;

import dao.GoodsDao;
import dao.GoodsTypeDao;
import dao.impl.GoodsDaoImpl;
import dao.impl.GoodsTypeDaoImpl;
import domain.Goods;
import domain.GoodsType;
import domain.GoodsWrapper;
import service.GoodsService;

import java.util.ArrayList;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    GoodsDao goodsDao = new GoodsDaoImpl();
    GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();

    @Override
    public List<Goods> GoodsListByTypeId(Integer typeid) {
        try {
            List<Goods> goods = goodsDao.queryByTypeId(typeid);
            if(goods!=null){
                return goods;
            }else{
                throw new RuntimeException("查询货物为空！");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Goods> GoodsPageByTypeId(Integer typeid, Integer pageNum) {
        try {
            List<Goods> goods = goodsDao.queryPageByTypeId(typeid, pageNum);
            if(goods!=null){
                return goods;
            }else{
                throw new RuntimeException("分页查询结果为空");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Goods GoodById(Integer id) {
        Goods good = null;
        try {
            good = goodsDao.queryId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return good;
    }

    @Override
    public List<GoodsWrapper> getAll() {
        List<Goods> goodsList = null;
        List<GoodsWrapper> goodsWrappers = new ArrayList<>();
        GoodsWrapper goodsWrapper = null;
        try {
            goodsList = goodsDao.queryAll();
            for (Goods goods : goodsList) {
                GoodsType goodsType = goodsTypeDao.queryById(goods.getTypeid());
                goodsWrapper = new GoodsWrapper(goods.getId(), goods.getName(), goods.getPubdate(), goods.getPrice(), goods.getIntro(), goodsType.getId(),goodsType.getName());
                goodsWrappers.add(goodsWrapper);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return goodsWrappers;
    }

    @Override
    public List<Goods> searchByLike(String search) {
        try {
            List<Goods> goodsList = goodsDao.queryByName(search);
            return goodsList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addGoods(Goods goods) {
        try {
            goodsDao.insertGoods(goods);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
