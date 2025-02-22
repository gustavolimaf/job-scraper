package com.example.job_scraper.controller;

import com.example.job_scraper.service.JobScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JobScraperController {

    private final JobScraperService jobScraperService;

    @Autowired
    public JobScraperController(JobScraperService jobScraperService) {
        this.jobScraperService = jobScraperService;
    }

    @PostMapping("/scrape/ciee")
    public ResponseEntity<String> scrapeCIEEJobs() {
        jobScraperService.scrapeCIEEJobs();
        return ResponseEntity.ok("Vagas do CIEE foram coletadas com sucesso!");
    }
}
