package ie.floral.flower_website.controller;

import ie.floral.flower_website.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PagesController {

    @GetMapping("/")
    public String home() { return "home"; }


    @GetMapping("/about")
    public String about() { return "about"; }

    @GetMapping("/contact")
    public String contact() { return "contact"; }
}