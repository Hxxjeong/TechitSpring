package com.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class VehicleExamMain2 {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();

//        Car2 car = new Car2();
//        car.setManufacturer("Toyota");
//        car.setSeatCount(5);
//        entityManager.persist(car);
//
//        Car2 car2 = new Car2();
//        car2.setManufacturer("Hyundai");
//        car2.setSeatCount(4);
//        entityManager.persist(car2);
//
//        Truck2 truck = new Truck2();
//        truck.setManufacturer("Kia");
//        truck.setPayloadCapacity(20.0);
//        entityManager.persist(truck);
//
//        Truck2 truck2 = new Truck2();
//        truck2.setManufacturer("Avantte");
//        truck2.setPayloadCapacity(15.0);
//        entityManager.persist(truck2);

        // 데이터 조회
        List<Vehicle2> vehicles = entityManager.createQuery("SELECT v FROM Vehicle2 v", Vehicle2.class).getResultList();
        for (Vehicle2 vehicle : vehicles) {
            if (vehicle instanceof Car2) {
                Car2 car = (Car2) vehicle;
                log.info("Car: {}, Seats: {}", car.getManufacturer(), car.getSeatCount());
            } else if (vehicle instanceof Truck2) {
                Truck2 truck = (Truck2) vehicle;
                log.info("Truck: {}, Payload Capacity: {}", truck.getManufacturer(), truck.getPayloadCapacity());
            }
        }

        entityManager.getTransaction().commit();
    }
}
