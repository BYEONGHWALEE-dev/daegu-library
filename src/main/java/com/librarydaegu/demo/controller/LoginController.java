package com.librarydaegu.demo.controller;

import com.librarydaegu.demo.entity.renter.Renter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model theModel) {

        theModel.addAttribute("renter", new Renter());

        // return name of html(It's different from the parameter of GetMapping annotation)
        return "/login";
    }
}
