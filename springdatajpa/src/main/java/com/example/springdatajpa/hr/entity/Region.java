package com.example.springdatajpa.hr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "regions")
@Getter
@Setter
@NoArgsConstructor
public class Region {
    @Id
    @Column(name = "region_id")
    private Integer regionId;
    @Column(name = "region_name")
    private String regionName;

    @OneToMany
    private List<Country> countries;
}
