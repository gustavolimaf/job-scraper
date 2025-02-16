package com.example.job_scraper.repository;

import com.example.job_scraper.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório JPA para a entidade Vacancy.
 * Permite operações de CRUD no banco de dados.
 */
@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
}
