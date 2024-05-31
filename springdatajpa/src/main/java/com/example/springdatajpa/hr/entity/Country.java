package com.example.springdatajpa.hr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
public class Country {
    @Id
    @Column(name = "country_id")
    private String countryId;
    @Column(name = "country_name")
    private String countryName;

    // regionid FK
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany
    private List<Location> locations;
}
