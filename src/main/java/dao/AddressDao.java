package dao;

import domain.Address;

import java.util.List;

public interface AddressDao {
    List<Address> queryByUid(Integer uid);

    void InsertAddress(Integer uid, String name, String phone, String detail);

    void addressDelete(Integer id);

    void updateAddressById(Integer id, String name, String phone, String detail);

    void updateLevel(Integer id, int level);

    Address queryByAid(Integer aid);

    void addressDeleteByUid(Integer uid);
}
