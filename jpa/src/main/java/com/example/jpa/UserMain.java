package com.example.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserMain {
    private static final Logger log = LoggerFactory.getLogger(UserMain.class);

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

//        User user1 = new User("choi", "choi@gmail.com");

//        userDAO.createUser(user1);

//        log.info("Create user: " + user1.getName());
//        log.info("user email: {}", user1.getEmail());

        log.info("------ findUser() ------");
        User findUser = userDAO.findUser(1L);
        log.info("Found user: {}", findUser.getName());
    }
}
