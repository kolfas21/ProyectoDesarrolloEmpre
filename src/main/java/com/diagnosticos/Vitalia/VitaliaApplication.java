package com.diagnosticos.Vitalia;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class VitaliaApplication {

	public static void main(String[] args) {
		// se carga el archivo .env para obtener las credenciales de Google
		Dotenv dotenv = Dotenv.configure().load();
		System.setProperty("GOOGLE_CLIENT_ID", dotenv.get("GOOGLE_CLIENT_ID"));
		System.setProperty("GOOGLE_CLIENT_SECRET", dotenv.get("GOOGLE_CLIENT_SECRET"));
		SpringApplication.run(VitaliaApplication.class, args);
	}

}

