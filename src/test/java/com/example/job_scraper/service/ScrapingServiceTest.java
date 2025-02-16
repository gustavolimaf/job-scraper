package com.example.job_scraper.service;

import com.example.job_scraper.model.Vacancy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.testng.Assert.assertNotNull;

@SpringBootTest
public class ScrapingServiceTest {

    @Autowired
    private ScrapingService scrapingService;

    @Test
    public void testScrapeVacancies() {
        // Chama o método de scraping
        List<Vacancy> vacancies = scrapingService.scrapeVacancies();

        // Verifica se a lista não é nula (a lógica de filtro pode retornar lista vazia, dependendo do site)
        assertNotNull(vacancies, "A lista de vagas não deve ser nula");

        // Opcional: Se souber que deve retornar pelo menos uma vaga, pode usar:
        // assertTrue(vacancies.size() > 0, "Deve haver pelo menos uma vaga");
    }
}
