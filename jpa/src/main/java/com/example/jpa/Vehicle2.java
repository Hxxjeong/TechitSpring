package com.example.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)   // 상속 관계
public class Vehicle2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manufacturer;
}

@Entity
@Getter
@Setter
class Car2 extends Vehicle2 {
    private int seatCount;
}

@Entity
@Getter
@Setter
class Truck2 extends Vehicle2 {
    private double payloadCapacity;
}