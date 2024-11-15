package com.example.hibernatejparent.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Data;

@Entity
@Table(name = "landlords")
@Data
public class Landlord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contactPhone;

    @OneToMany(mappedBy = "landlord")
    private Set<Apartment> apartments;

    @OneToMany(mappedBy = "landlord")
    private Set<Rental> rentals;

}