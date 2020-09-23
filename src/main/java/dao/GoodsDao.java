package dao;

import domain.Goods;

import java.util.List;

public interface GoodsDao {
    List<Goods> queryByTypeId(Integer id);
    List<Goods> queryPageByTypeId(Integer id, Integer pageNum);
    Goods queryId(Integer id);

    List<Goods> queryAll();

    List<Goods> queryByName(String search);

    void insertGoods(Goods goods);
}
