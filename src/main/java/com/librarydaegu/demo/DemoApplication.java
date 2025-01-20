package com.librarydaegu.demo;

import com.librarydaegu.demo.dao.RenterDAO;
import com.librarydaegu.demo.entity.renter.Renter;
import com.librarydaegu.demo.entity.renter.RenterEmailPassword;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(RenterDAO renterDAO, PasswordEncoder passwordEncoder) {

		return runner -> {
			// putPassword(renterDAO, passwordEncoder);
			System.out.println("Welcome to Daegu Library");
		};
	}

	private void putPassword(RenterDAO renterDAO, PasswordEncoder passwordEncoder) {

		// make a password for Renter
		String rawpassword = "springstudent";

		// get all renters from database
		List<Renter> renters = renterDAO.getAllRenters();

		// put email and password in the table 'renter_email_password"
		for(Renter theRenter: renters) {

			// define new fields for inserting in RenterEmailPassword
			String theEmail = theRenter.getEmail();
			String thePas = passwordEncoder.encode(rawpassword);

			// insert into the database
			renterDAO.addRenterEmailPasswordWithRenter(theEmail, thePas, theRenter);
		}
	}

}


