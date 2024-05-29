package com.example.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)   // 상속 관계
public class Vehicle3 {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String manufacturer;
}

@Entity
@Getter
@Setter
class Car3 extends Vehicle3 {
    private int seatCount;
}

@Entity
@Getter
@Setter
class Truck3 extends Vehicle3 {
    private double payloadCapacity;
}