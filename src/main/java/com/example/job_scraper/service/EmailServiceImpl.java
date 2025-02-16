package com.example.job_scraper.service;

import com.example.job_scraper.model.Vacancy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço de e-mail.
 * Utiliza JavaMailSender para disparar as mensagens.
 * Ajuste os endereços de e-mail para os desejados.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendVacancy(Vacancy vacancy) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            // E-mail de exemplo: altere conforme necessário
            msg.setTo("destinatario1@example.com");
            msg.setSubject("Email enviado pelo método sendVacancy");
            msg.setText("Detalhes da vaga:\n"
                    + "Código: " + vacancy.getCode() + "\n"
                    + "Título: " + vacancy.getTitle() + "\n"
                    + "Link: " + vacancy.getLink() + "\n"
                    + "Descrição: " + vacancy.getDescription());
            mailSender.send(msg);
            log.info("Email enviado (sendVacancy) para a vaga: {}", vacancy.getCode());
        } catch (Exception e) {
            log.error("Erro ao enviar e-mail (sendVacancy): ", e);
        }
    }

    @Override
    public void sendVacancyAlert(Vacancy vacancy) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            // E-mail de exemplo: altere conforme necessário
            msg.setTo("destinatario2@example.com");
            msg.setSubject("Nova vaga encontrada (sendVacancyAlert)!");
            msg.setText("Detalhes da vaga:\n"
                    + "Código: " + vacancy.getCode() + "\n"
                    + "Título: " + vacancy.getTitle() + "\n"
                    + "Link: " + vacancy.getLink() + "\n"
                    + "Descrição: " + vacancy.getDescription());
            mailSender.send(msg);
            log.info("Email enviado (sendVacancyAlert) para a vaga: {}", vacancy.getCode());
        } catch (Exception e) {
            log.error("Erro ao enviar e-mail (sendVacancyAlert): ", e);
        }
    }
}
