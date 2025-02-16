package com.example.job_scraper.service;

import com.example.job_scraper.model.Vacancy;

/**
 * Interface para o serviço de envio de e-mails.
 * - sendVacancy: método genérico para enviar dados de uma vaga (pouco usado).
 * - sendVacancyAlert: método para envio de alerta de nova vaga.
 */
public interface EmailService {
    void sendVacancy(Vacancy vacancy);
    void sendVacancyAlert(Vacancy vacancy);
}
