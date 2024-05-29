package com.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class VehicleExamMain1 {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();

//        Car car = new Car();
//        car.setManufacturer("Toyota");
//        car.setSeatCount(5);
//        entityManager.persist(car);
//
//        Car car2 = new Car();
//        car2.setManufacturer("Hyundai");
//        car2.setSeatCount(4);
//        entityManager.persist(car2);
//
//        Truck truck = new Truck();
//        truck.setManufacturer("Kia");
//        truck.setPayloadCapacity(20.0);
//        entityManager.persist(truck);

        // 결과 1개
        Car car = entityManager.find(Car.class, 1L);
        log.info("Car name: {}", car.getManufacturer());

        // 결과 여러 개 (JPQL 사용)
        List<Vehicle> vehicles = entityManager.createQuery("select v from Vehicle v", Vehicle.class).getResultList();
        for (Vehicle vehicle : vehicles) {
            if(vehicle instanceof Car) {    // 형변환을 위한 if
                Car car1 = (Car)vehicle;
                log.info("Car info: {}, {}", car1.getManufacturer(), car1.getSeatCount());
            }
            else if(vehicle instanceof Truck) {
                Truck truck = (Truck) vehicle;
                log.info("Truck info: {}, {}", truck.getManufacturer(), truck.getPayloadCapacity());
            }
        }

        entityManager.getTransaction().commit();
    }
}
