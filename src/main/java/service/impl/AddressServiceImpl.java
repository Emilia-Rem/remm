package service.impl;

import dao.AddressDao;
import dao.impl.AddressDaoImpl;
import domain.Address;
import service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    private AddressDao addressDao = new AddressDaoImpl();
    @Override
    public List<Address> searchByUid(Integer uid) {
        try {
            List<Address> addresses = addressDao.queryByUid(uid);
            return addresses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addAddress(Integer uid, String name, String phone, String detail) {
        try {
            addressDao.InsertAddress(uid,name,phone,detail);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            addressDao.addressDelete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateById(Integer id, String name, String phone, String detail) {
        try {
            addressDao.updateAddressById(id,name,phone,detail);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateLevelById(Integer id,int level) {
        try {
            addressDao.updateLevel(id,level);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
