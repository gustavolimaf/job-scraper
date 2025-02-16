package com.example.job_scraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Classe principal da aplicação Spring Boot.
 * Anotações:
 *  - @SpringBootApplication: habilita a configuração automática do Spring.
 *  - @EnableScheduling: permite usar @Scheduled para agendar tarefas.
 */
@SpringBootApplication
@EnableScheduling
public class JobScraperApplication {
	public static void main(String[] args) {
		SpringApplication.run(JobScraperApplication.class, args);
	}
}
