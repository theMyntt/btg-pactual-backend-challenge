package com.gabrielaraujo.order_processor_service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderProcessorServiceApplication {

	public static void main(String[] args) {
		var dotnev = Dotenv.configure().load();

		var postgresUrl = dotnev.get("POSTGRES_URL");
		var postgresUser = dotnev.get("POSTGRES_USER");
		var postgresPassword = dotnev.get("POSTGRES_PASSWORD");

		var jpaDdlAuto = dotnev.get("JPA_DDL_AUTO", "validate");
		var jpaShowSql = dotnev.get("JPA_SHOW_SQL", "true");

		var rabbitmqUrl = dotnev.get("RABBITMQ_URL");
		var rabbitmqPort = dotnev.get("RABBITMQ_PORT", "5672");
		var rabbitmqUser = dotnev.get("RABBITMQ_USER");
		var rabbitmqPassword = dotnev.get("RABBITMQ_PASSWORD");

		if (postgresUrl == null || postgresUrl.isBlank()) {
			throw new RuntimeException("The 'POSTGRES_URL' environment variable must not be null, empty, or blank.");
		}

		if (postgresUser == null || postgresUser.isBlank()) {
			throw new RuntimeException("The 'POSTGRES_USER' environment variable must not be null, empty, or blank.");
		}

		if (postgresPassword == null || postgresPassword.isBlank()) {
			throw new RuntimeException("The 'POSTGRES_PASSWORD' environment variable must not be null, empty, or blank.");
		}

		if (jpaDdlAuto == null || jpaDdlAuto.isBlank()) {
			throw new RuntimeException("The 'JPA_DDL_AUTO' environment variable must not be null, empty, or blank.");
		}

		if (jpaShowSql == null || jpaShowSql.isBlank()) {
			throw new RuntimeException("The 'JPA_SHOW_SQL' environment variable must not be null, empty, or blank.");
		}

		if (rabbitmqUrl == null || rabbitmqUrl.isBlank()) {
			throw new RuntimeException("The 'RABBITMQ_URL' environment variable must not be null, empty, or blank.");
		}

		if (rabbitmqPort == null || rabbitmqPort.isBlank()) {
			throw new RuntimeException("The 'RABBITMQ_PORT' environment variable must not be null, empty, or blank.");
		}

		if (rabbitmqUser == null || rabbitmqUser.isBlank()) {
			throw new RuntimeException("The 'RABBITMQ_USER' environment variable must not be null, empty, or blank.");
		}

		if (rabbitmqPassword == null || rabbitmqPassword.isBlank()) {
			throw new RuntimeException("The 'RABBITMQ_PASSWORD' environment variable must not be null, empty, or blank.");
		}

		dotnev.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);

		SpringApplication.run(OrderProcessorServiceApplication.class, args);
	}

}
