package com.wmk.proxy;

public class UserServiceImpl implements UserService {
    public User login(Integer id, String password) {
        System.out.println("==========login()===========");
        return null;
    }

    public void register(User user) {
        System.out.println("==========register()===========");
    }

    public User findById(Integer id) {
        System.out.println("==========fingById()===========");
        return null;
    }
}
