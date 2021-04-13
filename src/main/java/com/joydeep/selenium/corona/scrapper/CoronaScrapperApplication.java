package com.joydeep.selenium.corona.scrapper;

import com.joydeep.selenium.corona.scrapper.domains.GlobalStatistics;
import com.joydeep.selenium.corona.scrapper.services.PersistenceService;
import com.joydeep.selenium.corona.scrapper.services.ReportingService;
import com.joydeep.selenium.corona.scrapper.services.ScrapperService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoronaScrapperApplication implements CommandLineRunner {

    private final ScrapperService scrapperService;
    private final PersistenceService persistenceService;
    private final ReportingService reportingService;

    public static void main(String[] args) {
        SpringApplication.run(CoronaScrapperApplication.class, args);
    }

    public CoronaScrapperApplication(@Qualifier("covid19scrapper") ScrapperService scrapperService, @Qualifier("covid19persistor") PersistenceService persistenceService,
            @Qualifier("covid19reporter") ReportingService reportingService) {
        this.scrapperService = scrapperService;
        this.persistenceService = persistenceService;
        this.reportingService = reportingService;
    }

    @Override
    public void run(String... args) throws Exception {
        scrapperService.navigate();
        GlobalStatistics globalStatistics = (GlobalStatistics) scrapperService.scrape();
        persistenceService.persist(globalStatistics);
        reportingService.prepare(globalStatistics);
    }

}
