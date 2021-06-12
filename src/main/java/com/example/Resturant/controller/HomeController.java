package com.example.Resturant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getIndex(Model model) {
        return "index";
    }

    @GetMapping("/checkout")
    public String getCheckout(Model model) {
        return "checkout";
    }

}
