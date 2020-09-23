package dao.impl;

import dao.AddressDao;
import dao.UserDao;
import domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DruidUtils;
import utils.StringUntils;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    QueryRunner qr = new QueryRunner(DruidUtils.getDateSource());
    @Override
    public User queryByName(String username) {
        String sql = "select * from tb_user where username=?";
        try {
            User query = qr.query(sql, new BeanHandler<>(User.class),username);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException("根据用户名查询失败！");
        }
    }

    @Override
    public boolean insertUser(User user) {
        String sql = "insert into tb_user() value(?,?,?,?,?,?,?,?)";
        Object[] params = {"0",user.getUsername(),user.getPassword(),user.getEmail(),user.getGender(),user.getFlag(),user.getRole(),user.getCode()};
        try {
            if(qr.update(sql,params)!=0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("插入失败");
        }
        return false;
    }

    @Override
    public User queryByNameAndPwd(String username, String password) {
        String sql = "select * from tb_user where username=? and password=?";
        Object[] params = {username,password};
        try {
            return qr.query(sql, new BeanHandler<>(User.class),params);
        } catch (SQLException e) {
            throw new RuntimeException("根据账号密码查询失败");
        }
    }

    @Override
    public List<User> queryAll(Integer flag) {
        String sql = "select * from tb_user where flag=?;";
        try {
            List<User> userList = qr.query(sql, new BeanListHandler<>(User.class),flag);
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException("查询所有用户异常");
        }
    }

    @Override
    public User queryById(Integer id) {
        String sql = "select * from tb_user where id=?";
        try {
            User query = qr.query(sql, new BeanHandler<>(User.class),id);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException("根据用户名查询失败！");
        }
    }

    @Override
    public void deleteUserById(Integer uid) {
        AddressDao addressDao = new AddressDaoImpl();
        String sql = "delete from tb_user where id=?";
        try {
            addressDao.addressDeleteByUid(uid);
            qr.update(sql,uid);
        } catch (SQLException e) {
            throw new RuntimeException("根据uid删除用户失败！");
        }
    }

    @Override
    public List<User> queryByNameAndGender(String username, String gender) {
        String sql = null;
        try {
            if(StringUntils.isEmpty(username)){
                sql = "select * from tb_user where gender=?";
                Object[] params = {gender};
                List<User> userList = qr.query(sql, new BeanListHandler<>(User.class), params);
                return userList;
            }
            else if(StringUntils.isEmpty(gender)){
                sql = "select * from tb_user where username=?";
                Object[] params = {username};
                List<User> userList = qr.query(sql, new BeanListHandler<>(User.class), params);
                return userList;
            }else {
                sql = "select * from tb_user where username=?&&gender=?";
                Object[] params = {username,gender};
                List<User> userList = qr.query(sql, new BeanListHandler<>(User.class), params);
                return userList;
            }
        } catch (SQLException e) {
            throw new RuntimeException("根据用户名和性别查询失败！");
        }

    }

    @Override
    public void updateUserCode(User user) {
        String sql = "update tb_user set code=?;";
        try {
            qr.update(sql,"");
        } catch (SQLException e) {
            throw new RuntimeException("删除激活用户的激活码失败！");
        }
    }

    @Override
    public void updateFlag(int i) {
        String sql = "update tb_user set flag=?";
        try {
            qr.update(sql,i);
        } catch (SQLException e) {
            throw new RuntimeException("更新用户激活状态失败！");
        }
    }

}
