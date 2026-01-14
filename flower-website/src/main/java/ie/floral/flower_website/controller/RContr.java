package ie.floral.flower_website.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RContr {
    @GetMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}