package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface userDao {

    public User findByUsername(String username);

    public void save(User user);

    User findUserByCode(String code);

    void updateStatus(User user);

    User findUserByUsernameAndPassword(String username, String password);
}
