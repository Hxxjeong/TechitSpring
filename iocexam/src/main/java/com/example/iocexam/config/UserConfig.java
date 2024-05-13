package com.example.iocexam.config;

import com.example.iocexam.controller.UserContoller;
import com.example.iocexam.dao.UserDao;
import com.example.iocexam.dao.UserDaoImpl;
import com.example.iocexam.service.UserService;
import com.example.iocexam.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@ComponentScan("com.example.iocexam")
public class UserConfig {
//     IoC Container에게 Bean 등록
//     1. Java Config 이용
    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public UserService userService(UserDao userDao) {
        return new UserServiceImpl(userDao);

        // setter 주입하는 경우
/*        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(userDao);
        return userService;*/
    }

    @Bean
    public UserContoller userContoller(UserService userService) {
        return new UserContoller(userService);
    }

    // 2. ComponentScan
    // DAO, Service, Controller에 Component 명시
    // @ComponentScan("com.example.iocexam") 어노테이션 사용

    // 기본은 생성자 주입이기 때문에 setter나 필드 주입을 사용하려면 @Autowired 어노테이션 필요
}
