package com.example.job_scraper.service;

import com.example.job_scraper.model.JobListing;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

@Service
public class JobScraperService {

    private final JobListingRepository jobListingRepository;

    public JobScraperService(JobListRepository jobListRepository){
        this.jobListingRepository = jobListRepository;
    }

    public void scrapeCIEEJobs() {
        String baseUrl = "https://portal.ciee.org.br/quero-uma-vaga/";

        try {
            Document doc = Jsoup.connect(baseUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36...")
                    .timeout(10000)
                    .get();

            // Seleciona o container principal das vagas
            Elements jobModals = doc.select("div.modal-body");

            for (Element modal : jobModals) {
                JobListing job = new JobListing();

                // Código da vaga
                Element codeElement = modal.selectFirst("div.cod-vaga");
                if (codeElement != null) {
                    job.setCode(codeElement.text());
                }

                // Tipo de vaga
                Element typeElement = modal.selectFirst("h2.tipo-vaga");
                if (typeElement != null) {
                    job.setType(typeElement.text());
                }

                // Área
                Element areaElement = modal.selectFirst("li.info-area span");
                if (areaElement != null) {
                    job.setArea(areaElement.text());
                }

                // Localização
                Element locationElement = modal.selectFirst("li.info-local span");
                if (locationElement != null) {
                    job.setLocation(locationElement.text());
                }

                // Descrições
                Element companyDescElement = modal.selectFirst("div.descricao-empresa");
                Element jobDescElement = modal.selectFirst("div.descricao");
                Element activitiesElement = modal.selectFirst("div.atividades");

                if (jobDescElement != null) job.setJobDescription(jobDescElement.text());
                if (activitiesElement != null) job.setActivities(activitiesElement.text());

                jobListingRepository.save(job);
            }

        } catch (IOException e) {
            System.err.println("Erro no scraping: " + e.getMessage());
        }
    }
}
