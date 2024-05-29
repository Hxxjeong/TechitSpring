package com.example.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   // 상속 관계
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)   // column 이름과 type
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manufacturer;
}

@Entity
@Getter
@Setter
@DiscriminatorValue("CAR") // column 값
class Car extends Vehicle {
    private int seatCount;
}

@Entity
@Getter
@Setter
@DiscriminatorValue("TRUCK")
class Truck extends Vehicle {
    private double payloadCapacity;
}