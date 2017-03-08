package com.github.benyzhous.springboot.sharding.jdbc.masterslave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.benyzhous.springboot.sharding.jdbc.masterslave.service.OrderServcie;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	OrderServcie orderService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.err.println(orderService.selectAll().toString());
	}

}
