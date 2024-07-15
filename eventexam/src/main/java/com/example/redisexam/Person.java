package com.example.redisexam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("persons")
@Getter
@Setter
@ToString
public class Person implements Serializable {
    @Id
    private String id;
    private String name;
    private int age;
}
