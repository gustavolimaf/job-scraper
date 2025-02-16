package com.example.job_scraper.controller;

import com.example.job_scraper.repository.VacancyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controlador para exibir e gerenciar as vagas no navegador.
 * Endpoints:
 *  - /vacancies: lista todas as vagas (Thymeleaf).
 *  - /vacancies/delete/{id}: exclui uma vaga pelo ID.
 */
@Controller
public class VacancyController {
    private final VacancyRepository vacancyRepository;

    // Injeta o repositório via construtor
    public VacancyController(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    @GetMapping("/vacancies")
    public String vacancies(Model model) {
        // Adiciona ao modelo a lista de vagas para exibir no template vacancies.html
        model.addAttribute("vacancies", vacancyRepository.findAll());
        return "vacancies";
    }

    @GetMapping("/vacancies/delete/{id}")
    public String deleteVacancy(@PathVariable long id) {
        // Exclui a vaga pelo ID e redireciona para a listagem
        vacancyRepository.deleteById(id);
        return "redirect:/vacancies";
    }
}
