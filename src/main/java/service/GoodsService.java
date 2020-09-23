package service;

import domain.Goods;
import domain.GoodsWrapper;

import java.util.List;

public interface GoodsService {
    //通过类别ID查询所有商品
    List<Goods> GoodsListByTypeId(Integer typeid);
    //通过类别ID分页查询商品
    List<Goods> GoodsPageByTypeId(Integer typeid, Integer pageNum);
    //通过货物ID查询商品

    Goods GoodById(Integer id);

    List<GoodsWrapper> getAll();

    List<Goods> searchByLike(String search);

    void addGoods(Goods goods);
}
