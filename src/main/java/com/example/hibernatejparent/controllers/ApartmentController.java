package com.example.hibernatejparent.controllers;

import com.example.hibernatejparent.entities.Apartment;
import com.example.hibernatejparent.reposetories.ApartmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @GetMapping("/all")
    public String getAllLandlords(Model model) {
        List<Apartment> clients = apartmentRepository.findAll();
        model.addAttribute("apartments", clients);
        return "apartments";
    }
}
