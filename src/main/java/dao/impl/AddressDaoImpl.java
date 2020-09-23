package dao.impl;

import dao.AddressDao;
import domain.Address;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DruidUtils;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class AddressDaoImpl implements AddressDao {
    QueryRunner qr = new QueryRunner(DruidUtils.getDateSource());
    @Override
    public List<Address> queryByUid(Integer uid) {
        String sql = "select * from tb_address where uid=?";
        try {
            List<Address> addressList = qr.query(sql, new BeanListHandler<>(Address.class), uid);
            return addressList;
        } catch (SQLException e) {
            throw new RuntimeException("根据uid查询地址失败！");
        }
    }

    @Override
    public void InsertAddress(Integer uid, String name, String phone, String detail) {
        String sql = "insert into tb_address(id,detail,name,phone,uid,level) value(?,?,?,?,?,?);";
        Object[] params = {0,detail,name,phone,uid,0};
        try {
            int update = qr.update(sql, params);
            if(update==0){
                throw new RuntimeException("插入地址失败！");
            }
        } catch (SQLException e) {
            throw new RuntimeException("添加地址失败！");
        }
    }

    @Override
    public void addressDelete(Integer id) {
        String sql = "delete from tb_address where id=?;";
        try {
            qr.update(sql,id);
        } catch (SQLException e) {
            throw new RuntimeException("根据地址id删除地址失败！");
        }
    }

    @Override
    public void addressDeleteByUid(Integer uid) {
        String sql = "delete from tb_address where uid=?;";
        try {
            qr.update(sql,uid);
        } catch (SQLException e) {
            throw new RuntimeException("根据地址id删除地址失败！");
        }
    }

    @Override
    public void updateAddressById(Integer id, String name, String phone, String detail) {

        String sql = "update tb_address set name=?,phone=?,detail=? where id=?;";
        Object[] params = {name,phone,detail,id};
        System.out.println(Arrays.toString(params));
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException("更新地址失败！");
        }
    }
    @Override
    public void updateLevel(Integer id,int level){
        String sql = "update tb_address set level=? where id=?";
        Object[] params = {level,id};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw  new RuntimeException("更新默认状态失败!");
        }
    }

    @Override
    public Address queryByAid(Integer aid) {
        String sql = "select * from tb_address where id=?";
        try {
            Address address = qr.query(sql, new BeanHandler<>(Address.class), aid);
            return address;
        } catch (SQLException e) {
            throw new RuntimeException("根据aid查询地址失败！");
        }
    }
}
