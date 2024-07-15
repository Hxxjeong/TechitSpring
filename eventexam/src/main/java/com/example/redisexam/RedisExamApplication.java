package com.example.redisexam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class RedisExamApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(RedisExamApplication.class, args);
    }

    @Autowired
    private PersonService personService;

    @Autowired
    private RedisService redisService;

    @Override
    public void run(String... args) throws Exception {
        Person person = new Person();
        person.setId("test");
        person.setName("test one");
        person.setAge(20);

//        personService.save(person);
//
//        Optional<Person> test = personService.findById("test");
//        System.out.println(test.get().getAge());
//
//        Iterable<Person> all = personService.findAll();
//        all.forEach(System.out::println);

        // RedisService 이용
        String key = "testKey";
        String value = "{\"name\":\"test one\",\"age\":20}";    // JSON 형태로 작성

        redisService.saveJsonWithTTL(key, value, 20);

        String json = redisService.getJson(key);
        System.out.println("JSON" + json);

        Thread.sleep(21000);
        System.out.println("JSON" + json);
    }
}
