package com.example.hibernatejparent.reposetories;

import com.example.hibernatejparent.entities.Client;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameContainingIgnoreCase(String name);

    List<Client> findByContactPhoneContaining(String contactPhone);

    List<Client> findByRentalWishesContainingIgnoreCase(String rentalWishes);

    @Query("SELECT c FROM Client c JOIN c.rental r WHERE r.startDate >= :startDate")
    List<Client> findClientsWhoRentedWithinMonth(@Param("startDate") LocalDate startDate);

    @Query("SELECT c FROM Client c JOIN c.rental r WHERE r.endDate BETWEEN :now AND :oneMonthLater")
    List<Client> findClientsWithRentalEndingInMonth(@Param("now") LocalDate now,
            @Param("oneMonthLater") LocalDate oneMonthLater);

    @Query("SELECT c FROM Client c JOIN c.rental r WHERE r.endDate IS NOT NULL AND r.startDate IS NOT NULL AND (r.endDate - r.startDate) < 30")
    List<Client> findClientsWithAverageRentalLessThanMonth();

    @Query("SELECT c FROM Client c JOIN c.rental r WHERE r.endDate IS NOT NULL AND r.startDate IS NOT NULL AND (r.endDate - r.startDate) > 365")
    List<Client> findClientsWithAverageRentalMoreThanYear();

}


