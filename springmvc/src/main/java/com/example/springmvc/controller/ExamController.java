package com.example.springmvc.controller;

import com.example.springmvc.domain.Item;
import com.example.springmvc.domain.Product;
import com.example.springmvc.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ExamController {
    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("welcomeMsg", "hello");

        List<Item> items = Arrays.asList(
                new Item("pen", 3000),
                new Item("paper", 500),
                new Item("phone", 12000)
        );

        model.addAttribute("items", items);

        return "welcome";
    }

    @GetMapping("/product")
    public String product(Model model) {
        model.addAttribute("list", "상품 목록");

        List<Product> products = new ArrayList<>(Arrays.asList(
                new Product(1, "Apple", 1.20),
                new Product(2, "Banana", 0.75),
                new Product(3, "Cherry", 2.05)
        ));

        model.addAttribute("products", products);

        return "product";
    }

    @GetMapping("/example")
    public String example(Model model) {
        model.addAttribute("username", "kim");
        model.addAttribute("isAdmin", true);
        model.addAttribute("languages", new String[]{"Korean", "English", "Franch"});

        return "example";
    }

    // user 사용자 목록 출력
    @GetMapping("/user")
    public String users(Model model) {
        List<User> users = Arrays.asList(
                new User("kim", true),
                new User("lee", false)
        );

        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<String> items = Arrays.asList("item 1", "item 2", "item 3", "item 4");
        model.addAttribute("items", items);

        return "list";
    }
}
