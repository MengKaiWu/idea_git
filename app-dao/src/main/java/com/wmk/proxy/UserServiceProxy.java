package com.wmk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class UserServiceProxy extends Proxy implements UserService {

    public UserServiceProxy(InvocationHandler ih){
        super(ih);
    }
    public User login(Integer id, String password) {
        return null;
    }

    public void register(User user) {

    }

    public User findById(Integer id) {
        return null;
    }
}
