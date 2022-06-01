package it.univr.webapp.controller;

import it.univr.webapp.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WebApplicationController {

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
