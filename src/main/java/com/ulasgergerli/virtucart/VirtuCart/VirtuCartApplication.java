package com.ulasgergerli.virtucart.VirtuCart;

import com.ulasgergerli.virtucart.VirtuCart.Seed.DatabaseSeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VirtuCartApplication implements CommandLineRunner {

	@Autowired
	private DatabaseSeederService databaseSeederService;

	public static void main(String[] args) {
		SpringApplication.run(VirtuCartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		databaseSeederService.seedDatabase();
	}
}
