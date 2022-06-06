package it.univr.webapp.controller;

import it.univr.webapp.models.OrdersEntity;
import it.univr.webapp.repositories.AgentsRepository;
import it.univr.webapp.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WebApplicationController {

    @Autowired
    private AgentsRepository agentsRepository;
    @Autowired
    private CustomersRepository customerRepository;


    @GetMapping("/test")
    public String test() {
        return "Ciao! Sono una pagina di test!";
    }

    @GetMapping("/customers")
    public String customers(Model model) {
        System.out.println("QUI");
        model.addAttribute("customers", customerRepository.findAll());
        System.out.println(customerRepository.findAll());
        System.out.println("QUA");
        return "index";
    }
}
