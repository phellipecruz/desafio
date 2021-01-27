package br.tech.xpto.api;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlfApplication {

	public static void main(String [] args) throws IOException {
		SpringApplication.run(AlfApplication.class, args);
	}

}
