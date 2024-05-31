package com.example.springdatajpa.hr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "locations")
@Getter
@Setter
@NoArgsConstructor
public class Location {
    @Id
    @Column(name = "location_id")
    private Integer locationId;
    @Column(name = "street_address")
    private String streetAddress;
    @Column(name = "postal_code")
    private String postalCode;
    private String city;
    @Column(name = "state_province")
    private String stateProvince;

    // countryid FK
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany
    private List<Department> departments;
}
