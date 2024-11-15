package com.example.hibernatejparent.reposetories;

import com.example.hibernatejparent.entities.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandlordRepository extends JpaRepository<Landlord, Long> {

}
