package com.example.job_scraper.scheduler;

import com.example.job_scraper.model.Vacancy;
import com.example.job_scraper.repository.VacancyRepository;
import com.example.job_scraper.service.EmailService;
import com.example.job_scraper.service.ScrapingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Classe responsável por agendar a execução da tarefa de scraping a cada 1 hora.
 * Aqui, não é mais necessário filtrar "Estágio", "Superior", "Informática", "Brasília - DF",
 * pois já fizemos isso dentro do ScrapingServiceImpl.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JobScheduler {

    private final ScrapingService scrapingService;
    private final VacancyRepository vacancyRepository;
    private final EmailService emailService;

    // Executa a cada 60 minutos (3600000 ms)
    @Scheduled(fixedRate = 3600000)
    public void runScrapingTask(){
        // Executa o scraping e obtém somente as vagas que atendem aos filtros
        List<Vacancy> vacancies = scrapingService.scrapeVacancies();

        // Para cada vaga, salva no banco e envia o e-mail
        for (Vacancy vacancy : vacancies) {
            vacancyRepository.save(vacancy);
            emailService.sendVacancyAlert(vacancy);
        }
        log.info("Scraping finalizado. Vagas obtidas (após filtro): {}", vacancies.size());
    }
}
