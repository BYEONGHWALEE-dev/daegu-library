package com.librarydaegu.demo.dao;

import com.librarydaegu.demo.entity.renter.Renter;
import com.librarydaegu.demo.entity.renter.RenterEmailPassword;

import java.util.List;

public interface RenterDAO {

    // find functions

    Renter findRenterById(int id);

    List<Renter> getAllRenters();

    Renter findByEmail(String email);

    RenterEmailPassword findRenterEmailPasswordById(int id);

    // add functions
    void addRenterEmailPasswordWithRenter(String email, String password, Renter renter);

    // delete functions
    void deleteAllRenterEmailPassword();
}
