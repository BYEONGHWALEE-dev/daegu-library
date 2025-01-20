package com.librarydaegu.demo.dao;

import com.librarydaegu.demo.entity.renter.Renter;
import com.librarydaegu.demo.entity.renter.RenterEmailPassword;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RenterDAOImpl implements RenterDAO {

    // define EntityManager to access to database
    private EntityManager entityManager;

    // define constructor
    // Autowired annotation is not essential
    @Autowired
    public RenterDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // find functions

    @Override
    public Renter findRenterById(int id) {

        Renter theRenter = entityManager.find(Renter.class, id);

        return theRenter;
    }

    @Override // get all renters from renter database
    public List<Renter> getAllRenters() {

        List<Renter> renters = new ArrayList<>();

        TypedQuery<Renter> theQuery = entityManager.createQuery("SELECT r FROM Renter r", Renter.class);

        renters = theQuery.getResultList();

        return renters;
    }

    @Override // find the renter By email
    public Renter findByEmail(String email) {
        TypedQuery<Renter> theQuery = entityManager.createQuery("SELECT r FROM Renter r WHERE r.email = :email", Renter.class);

        theQuery.setParameter("email", email);

        return theQuery.getSingleResult();
    }

    @Override // find RenterEmailPassword by id
    public RenterEmailPassword findRenterEmailPasswordById(int id) {

        RenterEmailPassword theRenterEmailpassword = entityManager.find(RenterEmailPassword.class, id);

        return theRenterEmailpassword;

    }

    // add functions

    @Override
    @Transactional // add RenterEmailPassword with Renter
    public void addRenterEmailPasswordWithRenter(String email, String password, Renter renter) {

        // add an object with entitymanager
        RenterEmailPassword theRenterEmailPassword = new RenterEmailPassword(email, password);
        theRenterEmailPassword.setRenter(renter);

        entityManager.persist(theRenterEmailPassword);
    }

    // delete function

    @Override
    @Transactional // delete all renter email password
    public void deleteAllRenterEmailPassword() {

        // create query and implement
        entityManager.createQuery("DELETE FROM RenterEmailPassword ").executeUpdate();
    }


}
