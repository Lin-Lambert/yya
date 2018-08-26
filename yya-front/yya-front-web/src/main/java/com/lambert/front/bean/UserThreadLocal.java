package com.lambert.front.bean;

import com.lambert.manager.pojo.User;

public class UserThreadLocal {

    private static ThreadLocal<User> USER = new ThreadLocal<>();

    public static User get() {
        return USER.get();
    }

    public static void set(User user) {
        USER.set(user);
    }
}
