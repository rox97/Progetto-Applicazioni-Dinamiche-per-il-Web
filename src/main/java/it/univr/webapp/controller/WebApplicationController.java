package it.univr.webapp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class WebApplicationController {

    @GetMapping("/hello")
    public String index(Model model) {
        model.addAttribute("eventName", "test");
        return "index";
    }
}
