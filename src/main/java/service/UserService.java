package service;

import domain.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user);
    boolean searchName(String username);
    List<User> searchAll(Integer flag);
    User login(String username, String password);

    void removeUserById(Integer valueOf);

    List<User> searchNameAndGender(String username, String gender, Integer flag);
    //激活成功后清除用户激活码
    void clearUserCode(User user);

    User searchByName(String username);

    void changeFlag(int i);
}
