package com.example.hibernatejparent.controllers;

import com.example.hibernatejparent.entities.Landlord;
import com.example.hibernatejparent.reposetories.LandlordRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/landlords")
public class LandlordController {

    @Autowired
    private LandlordRepository landlordRepository;

    @GetMapping("/all")
    public String getAllLandlords(Model model) {
        List<Landlord> clients = landlordRepository.findAll();
        model.addAttribute("landlords", clients);
        return "landlords";
    }
}
