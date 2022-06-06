package it.univr.webapp.controller;

import it.univr.webapp.models.AgentsEntity;
import it.univr.webapp.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebApplicationController {

    @Autowired
    private CustomersRepository customerRepository;

    @GetMapping("/test")
    public ResponseEntity<List<AgentsEntity>> test() {
        List<AgentsEntity> agents = new ArrayList<AgentsEntity>();
        agents.add(new AgentsEntity("A123", "Andrea"));
        agents.add(new AgentsEntity("A456", "Simone"));

        //return "Ciao! Sono una pagina di test!";
        return ResponseEntity.ok(agents);
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
