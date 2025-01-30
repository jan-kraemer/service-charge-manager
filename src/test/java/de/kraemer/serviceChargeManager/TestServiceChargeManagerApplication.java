package de.kraemer.serviceChargeManager;

import org.springframework.boot.SpringApplication;

public class TestServiceChargeManagerApplication {

	public static void main(String[] args) {
		SpringApplication.from(ServiceChargeManagerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
