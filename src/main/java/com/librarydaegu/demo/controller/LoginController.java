package com.librarydaegu.demo.controller;

import com.librarydaegu.demo.dao.RenterDAO;
import com.librarydaegu.demo.entity.renter.Renter;
import com.librarydaegu.demo.entity.renter.RenterEmailPassword;
import jakarta.persistence.EntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class LoginController {

    RenterDAO renterDAO;

    public LoginController(RenterDAO renterDAO) {
        this.renterDAO = renterDAO;
    }

    // login page
    @GetMapping("/login")
    public String login(Model theModel) {

        // return name of html(It's different from the parameter of GetMapping annotation)
        return "login";
    }

    //sign up page
    @GetMapping("/signup")
    public String signup(Model theModel) {

        // Model for the renteremailpassword entity
        theModel.addAttribute("newRenterEmailPassword", new RenterEmailPassword());

        return "signup-form";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("newRenterEmailPassword") RenterEmailPassword newRenterEmailPassword) {

        // shouldn't persist data immediately because of plain password
        String plainPassword = newRenterEmailPassword.getPassword();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        newRenterEmailPassword.setPassword(encoder.encode(plainPassword));

        // define renter to put in RenterEmailPassword as a field
        Renter theRenter = new Renter();
        theRenter.setEmail(newRenterEmailPassword.getEmail());

        newRenterEmailPassword.setRenter(theRenter);

        // add the renter
        renterDAO.addRenterEmailPasswordWithRenter(newRenterEmailPassword);

        return "redirect:/login";
    }
}
