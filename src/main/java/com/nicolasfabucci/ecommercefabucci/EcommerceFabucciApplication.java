package com.nicolasfabucci.ecommercefabucci;

import com.nicolasfabucci.ecommercefabucci.repositories.ProductoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class EcommerceFabucciApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceFabucciApplication.class, args);
	}

}
