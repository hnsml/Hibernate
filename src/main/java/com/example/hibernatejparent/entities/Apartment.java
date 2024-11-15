package com.example.hibernatejparent.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;

@Entity
@Table(name = "apartments")
@Data
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private int numberOfRooms;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private Landlord landlord;

    @OneToMany(mappedBy = "apartment")
    private Set<Rental> rentals;


}