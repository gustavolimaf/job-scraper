package com.example.job_scraper.repository;

import com.example.job_scraper.model.Vacancy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VacancyRepositoryTest {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Test
    public void testSaveAndFind() {
        // Cria uma nova vaga de teste
        Vacancy vacancy = new Vacancy();
        vacancy.setCode("123456");
        vacancy.setTitle("Estágio");
        vacancy.setLink("?codigoVaga=123456");
        vacancy.setDescription("Estágio em TI - Brasília - DF");
        vacancy.setPublicationDate(LocalDateTime.now());

        // Salva a vaga
        Vacancy saved = vacancyRepository.save(vacancy);
        assertNotNull(saved.getId(), "A vaga salva deve ter um ID");

        // Recupera a vaga pelo ID
        Vacancy found = vacancyRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found, "A vaga deve ser encontrada pelo ID");
        assertEquals("123456", found.getCode(), "O código da vaga deve ser igual ao esperado");
    }
}