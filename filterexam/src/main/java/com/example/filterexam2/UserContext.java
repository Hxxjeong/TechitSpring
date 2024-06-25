package com.example.filterexam2;

// ThreadLocal을 이용해서 필요한 정보 저장
// ThreadLocal을 가지는 객체
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
