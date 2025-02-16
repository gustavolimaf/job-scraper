package com.example.job_scraper.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Entidade que representa uma vaga no banco de dados.
 * Campos:
 *  - id: Identificador auto-incrementado.
 *  - code: Código da vaga (ex.: "5476989").
 *  - title: Título ou tipo da vaga (ex.: "Estágio").
 *  - link: URL (href) para acessar a vaga.
 *  - description: Pode conter dados adicionais (por exemplo, "Superior - Informática - Asa Sul - Brasília - DF").
 *  - publicationDate: Data/hora de quando foi adicionada ao sistema.
 */
@Entity
@Data
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;        // Ex.: "5476989"
    private String title;       // Ex.: "Estágio"
    private String link;        // Ex.: "?codigoVaga=5476989"
    private String description; // Ex.: "Superior - Informática - Asa Sul - Brasília - DF"
    private LocalDateTime publicationDate;
}
