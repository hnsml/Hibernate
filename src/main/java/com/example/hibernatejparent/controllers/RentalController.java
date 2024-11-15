package com.example.hibernatejparent.controllers;

import com.example.hibernatejparent.entities.Rental;
import com.example.hibernatejparent.reposetories.RentalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalRepository rentalRepository;

    @GetMapping("/all")

    public String getAllLandlords(Model model) {
        List<Rental> clients = rentalRepository.findAll();
        model.addAttribute("rentals", clients);
        return "rentals";
    }
}
