package service;

import domain.Address;

import java.util.List;

public interface AddressService {
    //根据uid查找地址
    List<Address> searchByUid(Integer uid);
    //添加地址
    void addAddress(Integer uid, String name, String phone, String detail);
    //通过地址id删除地址
    void deleteById(Integer id);
    //通过地址id修改地址
    void updateById(Integer id, String name, String phone, String detail);
    ////通过地址id修改地址默认状态
    void updateLevelById(Integer id, int level);
}
