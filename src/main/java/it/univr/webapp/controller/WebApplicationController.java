package it.univr.webapp.controller;

import graphql.ExecutionResult;
import it.univr.webapp.repositories.CustomerRepository;
import it.univr.webapp.service.GraphQLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WebApplicationController {

    @Autowired
    private CustomerRepository customerRepository;

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
