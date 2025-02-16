package com.example.job_scraper.service;

import com.example.job_scraper.model.Vacancy;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do serviço de scraping.
 * Busca no site do CIEE e filtra pelas informações:
 *  - Tipo de vaga: "Estágio"
 *  - Nível de ensino: "Superior"
 *  - Área profissional: "Informática"
 *  - Cidade: "Brasília - DF"
 *  - Código da vaga: extraído de <span class="cod-vaga">
 */
@Slf4j
@Service
public class ScrapingServiceImpl implements ScrapingService {

    private static final String TARGET_URL = "https://portal.ciee.org.br/quero-uma-vaga/";

    @Override
    public List<Vacancy> scrapeVacancies() {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            // Conecta ao site e obtém o HTML
            Document doc = Jsoup.connect(TARGET_URL).get();

            // Cada vaga está em "a.vaga-row" (ex.: <a class="vaga-row tipo-vaga-Estágio" ...>)
            Elements vagaElements = doc.select("a.vaga-row");
            log.info("Quantidade de elementos de vaga encontrados: {}", vagaElements.size());

            for (Element el : vagaElements) {
                // Extrai o código da vaga
                String code = el.select("span.cod-vaga").text();
                // Extrai o tipo da vaga (ex.: "Estágio")
                String tipoVaga = el.select("h2.tipo-vaga").text();
                // Extrai a descrição (geralmente um <div class="descricao">)
                String desc = el.select("div.descricao").text();
                // Extrai a localidade e nível/área se existirem no <ul class="infos"> (ex.: "li.info-area", "li.info-local")
                // Observando que o site pode ter:
                //  <li class="info-area"><span>Superior - Informática -</span></li>
                //  <li class="info-local"><span>Asa Sul - Brasília - DF</span></li>

                String infoArea = el.select("li.info-area span").text();    // ex.: "Superior - Informática -"
                String infoLocal = el.select("li.info-local span").text();  // ex.: "Asa Sul - Brasília - DF"

                // Filtra se:
                //  1) tipoVaga == "Estágio"
                //  2) infoArea contém "Superior" e "Informática"
                //  3) infoLocal contém "Brasília - DF"
                boolean isEstagio = tipoVaga.equalsIgnoreCase("Estágio");
                boolean isSuperior = infoArea.toLowerCase().contains("superior");
                boolean isInformatica = infoArea.toLowerCase().contains("informática");
                boolean isBrasilia = infoLocal.toLowerCase().contains("brasília - df");

                // Se todos os filtros forem atendidos, adiciona a vaga
                if (isEstagio && isSuperior && isInformatica && isBrasilia) {
                    Vacancy v = new Vacancy();
                    v.setCode(code);                // ex.: "5476989"
                    v.setTitle(tipoVaga);           // "Estágio"
                    // Pega o link do atributo href
                    String href = el.attr("href");  // ex.: "?codigoVaga=5476989"
                    v.setLink(href);
                    // Monta a descrição com nível, área e local
                    // ou simplesmente guarda desc se quiser
                    String finalDesc = desc; // ex.: "Suporte técnico..."
                    // Se preferir, junte infoArea + infoLocal
                    if (!infoArea.isEmpty() || !infoLocal.isEmpty()) {
                        finalDesc += " / " + infoArea + " / " + infoLocal;
                    }
                    v.setDescription(finalDesc.trim());

                    v.setPublicationDate(LocalDateTime.now());
                    vacancies.add(v);
                }
            }
        } catch (Exception e) {
            log.error("Erro ao realizar scraping: ", e);
        }
        log.info("Vagas filtradas que atendem aos critérios: {}", vacancies.size());
        return vacancies;
    }
}