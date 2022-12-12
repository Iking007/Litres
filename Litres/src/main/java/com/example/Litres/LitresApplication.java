package com.example.Litres;

import com.example.Litres.Controller.SecretController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LitresApplication {

	public static void main(String[] args) {
		SpringApplication.run(LitresApplication.class, args);
		SecretController.newSecret();
	}

}
