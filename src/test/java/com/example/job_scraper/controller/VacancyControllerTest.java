package com.example.job_scraper.controller;

import com.example.job_scraper.model.Vacancy;
import com.example.job_scraper.repository.VacancyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VacancyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VacancyRepository vacancyRepository;

    @BeforeEach
    public void setup() {
        // Limpa o repositório antes de cada teste
        vacancyRepository.deleteAll();

        // Cria uma vaga de teste para exibição
        Vacancy vacancy = new Vacancy();
        vacancy.setCode("123456");
        vacancy.setTitle("Estágio");
        vacancy.setLink("?codigoVaga=123456");
        vacancy.setDescription("Estágio em TI - Brasília - DF");
        vacancy.setPublicationDate(LocalDateTime.now());
        vacancyRepository.save(vacancy);
    }

    @Test
    public void testVacanciesPage() throws Exception {
        mockMvc.perform(get("/vacancies"))
                .andExpect(status().isOk())
                .andExpect(view().name("vacancies"))
                .andExpect(model().attributeExists("vacancies"));
    }
}