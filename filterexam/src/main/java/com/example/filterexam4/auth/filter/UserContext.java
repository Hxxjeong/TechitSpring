package com.example.filterexam4.auth.filter;

import com.example.filterexam4.entity.User;

public class UserContext {
    // 초기화
    private static final ThreadLocal<User> USER_THREAD_LOCAL = ThreadLocal.withInitial(() -> null);

    public static void setUser(User user) {
        USER_THREAD_LOCAL.set(user);
    }

    public static User getUser() {
        return USER_THREAD_LOCAL.get();
    }

    public static void clear() {
        USER_THREAD_LOCAL.remove();
    }
}
