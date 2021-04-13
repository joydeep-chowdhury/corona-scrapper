package com.joydeep.selenium.corona.scrapper.services;

import com.joydeep.selenium.corona.scrapper.configurations.Covid19BusinessConfiguration;
import com.joydeep.selenium.corona.scrapper.domains.GlobalStatistics;
import com.joydeep.selenium.corona.scrapper.exceptions.KeyNotFoundException;
import com.joydeep.selenium.corona.scrapper.repositories.GlobalStatisticsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.joydeep.selenium.corona.scrapper.utilities.ScrapingUtilities.scrapeMainCounterValues;
import static com.joydeep.selenium.corona.scrapper.utilities.ScrapingUtilities.getCurrentlyInfectedPatients;
import static com.joydeep.selenium.corona.scrapper.utilities.ScrapingUtilities.getCasesWithAnOutcome;
import static com.joydeep.selenium.corona.scrapper.utilities.ScrapingUtilities.getInMildCondition;
import static com.joydeep.selenium.corona.scrapper.utilities.ScrapingUtilities.getInMildConditionPercentage;
import static com.joydeep.selenium.corona.scrapper.utilities.ScrapingUtilities.getInSeriousCondition;
import static com.joydeep.selenium.corona.scrapper.utilities.ScrapingUtilities.getInSeriousConditionPercentage;
import static com.joydeep.selenium.corona.scrapper.utilities.ScrapingUtilities.getRecoveredCount;
import static com.joydeep.selenium.corona.scrapper.utilities.ScrapingUtilities.getRecoveredPercentage;
import static com.joydeep.selenium.corona.scrapper.utilities.ScrapingUtilities.getDeathCount;
import static com.joydeep.selenium.corona.scrapper.utilities.ScrapingUtilities.getDeathPercentage;
import static com.joydeep.selenium.corona.scrapper.utilities.ScrapingUtilities.scrapeTableHeaderMap;
import static com.joydeep.selenium.corona.scrapper.utilities.ScrapingUtilities.scrapeCountryWiseCoronaDetails;
import static com.joydeep.selenium.corona.scrapper.domains.GlobalStatistics.GlobalStatisticsBuilder;

@Service
@Qualifier("covid19scrapper")
public class Covid19ScrapperService implements ScrapperService<GlobalStatistics> {
    private static final Logger logger = LoggerFactory.getLogger(Covid19ScrapperService.class);
    private final WebDriver webDriver;
    private final Covid19BusinessConfiguration covid19BusinessConfiguration;
    private final GlobalStatisticsRepository globalStatisticsRepository;

    public Covid19ScrapperService(WebDriver webDriver, Covid19BusinessConfiguration covid19BusinessConfiguration, GlobalStatisticsRepository globalStatisticsRepository) {
        this.webDriver = webDriver;
        this.covid19BusinessConfiguration = covid19BusinessConfiguration;
        this.globalStatisticsRepository = globalStatisticsRepository;
    }

    @Override
    public void navigate() {
        webDriver.navigate()
                 .to(covid19BusinessConfiguration.getWorldometersSite());
    }

    @Override
    public GlobalStatistics scrape() {
        Document document = Jsoup.parse(webDriver.getPageSource());
        Elements mainCounterElements = document.select("div[id=maincounter-wrap]");
        Elements panelElements = document.select("div[class=panel panel-default]");
        GlobalStatistics globalStatistics = null;
        try {
            String totalCoronavirusCases = scrapeMainCounterValues(mainCounterElements, covid19BusinessConfiguration.getTotalCoronavirusCasesKey());
            String totalCoronavirusDeaths = scrapeMainCounterValues(mainCounterElements, covid19BusinessConfiguration.getTotalCoronavirusDeathsKey());
            String totalCoronavirusRecovered = scrapeMainCounterValues(mainCounterElements, covid19BusinessConfiguration.getTotalCoronavirusRecoveredKey());
            String currentlyInfectedPatients = getCurrentlyInfectedPatients(panelElements, covid19BusinessConfiguration.getActiveCasesPanelHeadingKey());
            String casesWithAnOutcome = getCasesWithAnOutcome(panelElements, covid19BusinessConfiguration.getClosedCasesPanelHeadingKey());
            String inMildCondition = getInMildCondition(panelElements, covid19BusinessConfiguration.getActiveCasesPanelHeadingKey());
            String inMildConditionPercentage = getInMildConditionPercentage(panelElements, covid19BusinessConfiguration.getActiveCasesPanelHeadingKey());
            String inSeriousCondition = getInSeriousCondition(panelElements, covid19BusinessConfiguration.getActiveCasesPanelHeadingKey());
            String inSeriousConditionPercentage = getInSeriousConditionPercentage(panelElements, covid19BusinessConfiguration.getActiveCasesPanelHeadingKey());
            String recoveredCount = getRecoveredCount(panelElements, covid19BusinessConfiguration.getClosedCasesPanelHeadingKey());
            String recoveredPercentage = getRecoveredPercentage(panelElements, covid19BusinessConfiguration.getClosedCasesPanelHeadingKey());
            String deathCount = getDeathCount(panelElements, covid19BusinessConfiguration.getClosedCasesPanelHeadingKey());
            String deathPercentage = getDeathPercentage(panelElements, covid19BusinessConfiguration.getClosedCasesPanelHeadingKey());
            Map<Integer, String> headerIndexNameMap = scrapeTableHeaderMap(document);
            List<Map<String, String>> countryWiseCoronaDetails = scrapeCountryWiseCoronaDetails(document, headerIndexNameMap);
            globalStatistics = new GlobalStatistics.GlobalStatisticsBuilder(totalCoronavirusCases, totalCoronavirusDeaths,
                    totalCoronavirusRecovered).currentlyInfectedPatients(currentlyInfectedPatients)
                                              .casesWithAnOutcome(casesWithAnOutcome)
                                              .inMildCondition(inMildCondition)
                                              .inMildConditionPercentage(inMildConditionPercentage)
                                              .inSeriousCondition(inSeriousCondition)
                                              .inSeriousConditionPercentage(inSeriousConditionPercentage)
                                              .deathCount(deathCount)
                                              .deathPercentage(deathPercentage)
                                              .recoveredCount(recoveredCount)
                                              .recoveredPercentage(recoveredPercentage)
                                              .countryWiseCoronaDetails(countryWiseCoronaDetails)
                                              .build();

            logger.info("Global statistics {}", globalStatistics);

        } catch (KeyNotFoundException keyNotFoundException) {
            logger.error("Exiting application as configured key is not available: {}", keyNotFoundException.toString());
            System.exit(1);
        }
        return globalStatistics;
    }

}
