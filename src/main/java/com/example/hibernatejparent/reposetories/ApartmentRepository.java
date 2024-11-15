package com.example.hibernatejparent.reposetories;

import com.example.hibernatejparent.entities.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

}
