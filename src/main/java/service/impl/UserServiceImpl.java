package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public boolean addUser(User user) {
        try {
            if(userDao.queryByName(user.getUsername())==null){
                userDao.insertUser(user);
                return true;
            }
        } catch (Exception e) {
            new RuntimeException(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean searchName(String username) {
        try {
            if(userDao.queryByName(username)!=null){
                return true;
            }

        } catch (Exception e) {
            new RuntimeException(e.getMessage());
        }
        return false;
    }

    @Override
    public List<User> searchAll(Integer flag) {
        try {
            List<User> users = userDao.queryAll(flag);
            return users;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User login(String username, String password) {
        return userDao.queryByNameAndPwd(username,password);
    }

    @Override
    public void removeUserById(Integer uid) {
        try {
            User user = userDao.queryById(uid);
            if(user.getRole()==1){
                userDao.deleteUserById(uid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> searchNameAndGender(String username, String gender,Integer flag) {
        try {
            List<User> users = new ArrayList<>();
            List<User> userList = userDao.queryByNameAndGender(username, gender);
            for (User user : userList) {
                if(user.getFlag()==flag){
                    users.add(user);
                }
            }
            return users;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearUserCode(User user) {
        try {
            userDao.updateUserCode(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User searchByName(String username) {
        try {
            User user = userDao.queryByName(username);
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeFlag(int i) {
        userDao.updateFlag(i);
    }
}
