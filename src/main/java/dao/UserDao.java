package dao;

import domain.User;

import java.util.List;

public interface UserDao {
    User queryByName(String username);
    boolean insertUser(User user);

    User queryByNameAndPwd(String username, String password);
    //查找所有用户
    List<User> queryAll(Integer flag);
    User queryById(Integer id);
    //根据id删除用户
    void deleteUserById(Integer uid);

    List<User> queryByNameAndGender(String username, String gender);

    void updateUserCode(User user);

    void updateFlag(int i);
}
