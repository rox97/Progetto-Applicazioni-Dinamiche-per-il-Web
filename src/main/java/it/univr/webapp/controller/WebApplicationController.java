package it.univr.webapp.controller;

import it.univr.webapp.models.AgentsEntity;
import it.univr.webapp.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WebApplicationController {

    @Autowired
    private CustomersRepository customerRepository;

    @GetMapping("/test")
    public ResponseEntity<List<AgentsEntity>> test() {
        List<AgentsEntity> agents = new ArrayList<>();
        agents.add(new AgentsEntity("A123", "Andrea"));
        agents.add(new AgentsEntity("A456", "Simone"));
        agents.add(new AgentsEntity("A789", "Andrea"));

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

    @PostMapping(value = "/authentication")
    public ResponseEntity<String> auth(@RequestBody Map<String, String> request) {
        System.out.println("QUI");
        System.out.println(request);

        if(request.get("username").equals("a") && request.get("password").equals("a")) {
            return ResponseEntity.ok("admin");
        }
        else if(request.get("username").equals("z") && request.get("password").equals("z")){
            return ResponseEntity.ok("customer");
        }
        else {
            return ResponseEntity.ok("FAILURE");
        }
    }
}
