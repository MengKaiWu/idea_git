package com.wmk.proxy;

public interface UserService {
    //登陆
    public User login(Integer id,String password);
    //注册
    public void register(User user);
    //查找
    public User findById(Integer id);
}
