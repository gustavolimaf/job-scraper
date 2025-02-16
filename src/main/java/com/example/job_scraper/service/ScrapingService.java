package com.example.job_scraper.service;

import com.example.job_scraper.model.Vacancy;
import java.util.List;

/**
 * Interface para o serviço de scraping.
 * Responsável por extrair as vagas do site do CIEE.
 */
public interface ScrapingService {
    List<Vacancy> scrapeVacancies();
}
