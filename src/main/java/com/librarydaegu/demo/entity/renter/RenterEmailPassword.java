package com.librarydaegu.demo.entity.renter;

import jakarta.persistence.*;

@Entity
@Table(name = "renter_email_password")
public class RenterEmailPassword {

    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "renter_detail_id")
    private Renter renter;

    // define Constructors

    public RenterEmailPassword() {};

    public RenterEmailPassword(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // define getter/setter methods

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }


    @Override
    public String toString() {
        return "RenterEmailPassword{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
