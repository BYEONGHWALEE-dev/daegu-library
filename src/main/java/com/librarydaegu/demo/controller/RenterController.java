package com.librarydaegu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RenterController {

    @GetMapping("/renter-dashboard")
    public String renterDashboard() {

        return "/renter-dashboard";
    }
}
