package com.librarydaegu.demo.controller;

import com.librarydaegu.demo.dao.RenterDAO;
import com.librarydaegu.demo.entity.renter.Renter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RenterController {

    RenterDAO renterDAO;

    public RenterController(RenterDAO renterDAO) {
        this.renterDAO = renterDAO;
    }

    @GetMapping("/renter-dashboard")
    public String renterDashboard(Model theModel) {

        // take authenticated user to Controller from Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // get renter using email by DAO
        Renter renter = renterDAO.findByEmail(email);

        // put renter into the model
        theModel.addAttribute("renter", renter);

        return "/renter-dashboard";
    }
}

/*
        // create Query to find name based on email
        TypedQuery<Renter> theRenter = entityManager.createQuery("SELECT r FROM Renter r WHERE r.email = :email ", Renter.class);
        theRenter.setParameter("email", email);

        // get the result
        Renter renter = theRenter.getSingleResult();

*/