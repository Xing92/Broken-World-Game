package xing.brokenworldserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import xing.brokenworldserver.controller.SimpleController;

@SpringBootApplication
public class SpringBootConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(SimpleController.class, args);
	}
}
