package com.example.hibernatejparent.reposetories;

import com.example.hibernatejparent.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {

}
