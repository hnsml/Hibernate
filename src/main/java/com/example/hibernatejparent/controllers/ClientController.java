package com.example.hibernatejparent.controllers;

import com.example.hibernatejparent.entities.Client;
import com.example.hibernatejparent.reposetories.ClientRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/all")
    public String getAllClients(Model model) {
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/add")
    public String addClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "client-form";
    }

    @PostMapping("/save")
    public String saveClient(@ModelAttribute Client client) {
        clientRepository.save(client);
        return "redirect:/clients/all";
    }

    @GetMapping("/edit/{id}")
    public String editClientForm(@PathVariable Long id, Model model) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        model.addAttribute("client", client);
        return "client-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return "redirect:/clients/all";
    }

    @GetMapping("/search")
    public String searchClients(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "contactPhone", required = false) String contactPhone,
            @RequestParam(value = "rentalWishes", required = false) String rentalWishes,
            Model model) {
        List<Client> clients;
        if (name != null && !name.isEmpty()) {
            clients = clientRepository.findByNameContainingIgnoreCase(name);
        } else if (contactPhone != null && !contactPhone.isEmpty()) {
            clients = clientRepository.findByContactPhoneContaining(contactPhone);
        } else if (rentalWishes != null && !rentalWishes.isEmpty()) {
            clients = clientRepository.findByRentalWishesContainingIgnoreCase(rentalWishes);
        } else {
            clients = clientRepository.findAll();
        }
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/rentedWithinMonth")
    public String getClientsWhoRentedWithinMonth(Model model) {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        List<Client> clients = clientRepository.findClientsWhoRentedWithinMonth(oneMonthAgo);
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/rentalEndingInMonth")
    public String getClientsWithRentalEndingInMonth(Model model) {
        LocalDate now = LocalDate.now();
        LocalDate oneMonthLater = now.plusMonths(1);
        List<Client> clients = clientRepository.findClientsWithRentalEndingInMonth(now,
                oneMonthLater);
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/averageRentalLessThanMonth")
    public String getClientsWithAverageRentalLessThanMonth(Model model) {
        List<Client> clients = clientRepository.findClientsWithAverageRentalLessThanMonth();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/averageRentalMoreThanYear")
    public String getClientsWithAverageRentalMoreThanYear(Model model) {
        List<Client> clients = clientRepository.findClientsWithAverageRentalMoreThanYear();
        model.addAttribute("clients", clients);
        return "clients";
    }
}