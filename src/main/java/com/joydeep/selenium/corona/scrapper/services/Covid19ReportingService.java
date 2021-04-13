package com.joydeep.selenium.corona.scrapper.services;

import com.joydeep.selenium.corona.scrapper.configurations.Covid19BusinessConfiguration;
import com.joydeep.selenium.corona.scrapper.domains.CountryLevelCovid19Params;
import com.joydeep.selenium.corona.scrapper.domains.GlobalParams;
import com.joydeep.selenium.corona.scrapper.domains.GlobalStatistics;
import com.joydeep.selenium.corona.scrapper.exceptions.ReportGenerationFailedException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Qualifier("covid19reporter")
public class Covid19ReportingService implements ReportingService<GlobalStatistics> {
    private final static Logger logger = LoggerFactory.getLogger(Covid19ReportingService.class);
    private final Covid19BusinessConfiguration covid19BusinessConfiguration;
    private final static String GLOBAL_SHEET_NAME = "Global Covid19 Statistics";
    private final static String COUNTRY_WISE_SHEET_NAME = "Country Wise Covid19 Statistics";
    private final static String REPORT_EXTENSION = ".xlsx";
    private final static Integer HEADER_ROW_NUMBER = new Integer(0);
    private final static Integer DATA_ROW_START_NUMBER = new Integer(1);

    public Covid19ReportingService(Covid19BusinessConfiguration covid19BusinessConfiguration) {
        this.covid19BusinessConfiguration = covid19BusinessConfiguration;
    }

    @Override
    public void prepare(GlobalStatistics globalStatistics) {
        FileOutputStream out = null;
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            XSSFSheet globalSheet = createSheet(workbook, GLOBAL_SHEET_NAME);
            XSSFSheet countryWiseSheet = createSheet(workbook, COUNTRY_WISE_SHEET_NAME);
            createGlobalData(globalSheet, globalStatistics);
            createCountryWiseData(countryWiseSheet, globalStatistics);

            out = new FileOutputStream(new File(covid19BusinessConfiguration.getReportPath() + covid19BusinessConfiguration.getReportFileName()
                    + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + REPORT_EXTENSION));
            workbook.write(out);
            out.close();
            workbook.close();
        } catch (FileNotFoundException fileNotFoundException) {
            logger.error("Exiting application due to exception" + fileNotFoundException.getMessage());
            System.exit(1);
        } catch (IOException ioException) {
            logger.error("Exiting application due to exception" + ioException.getMessage());
            System.exit(1);
        } catch (Exception exception) {
            logger.error("Exiting application due to exception" + exception.getMessage());
            System.exit(1);
        } finally {
            logger.info("Report successfully generated");
        }

    }

    private static XSSFSheet createSheet(XSSFWorkbook workbook, String sheetName) {
        return workbook.createSheet(sheetName);
    }

    private static void createGlobalData(XSSFSheet globalSheet, GlobalStatistics globalStatistics) {
        GlobalParams[] globalParams = GlobalParams.values();
        createHeaderRow(globalSheet, Arrays.asList(globalParams)
                                           .stream()
                                           .map(param -> param.getName())
                                           .collect(Collectors.toList()));

        Map<String, String> dataMap = new LinkedHashMap<String, String>() {
            {
                put(GlobalParams.TOTAL_CORONAVIRUS_CASES.toString(), globalStatistics.getTotalCoronavirusCases());
                put(GlobalParams.TOTAL_CORONAVIRUS_DEATHS.toString(), globalStatistics.getTotalCoronavirusDeaths());
                put(GlobalParams.TOTAL_CORONAVIRUS_RECOVERED.toString(), globalStatistics.getTotalCoronavirusRecovered());
                put(GlobalParams.CURRENTLY_INFECTED_PATIENTS.toString(), globalStatistics.getCurrentlyInfectedPatients());
                put(GlobalParams.IN_MILD_CONDITION.toString(), globalStatistics.getInMildCondition());
                put(GlobalParams.IN_MILD_CONDITION_PERCENTAGE.toString(), globalStatistics.getInMildConditionPercentage());
                put(GlobalParams.IN_SERIOUS_CONDITION.toString(), globalStatistics.getInSeriousCondition());
                put(GlobalParams.IN_SERIOUS_CONDITION_PERCENTAGE.toString(), globalStatistics.getInSeriousConditionPercentage());
                put(GlobalParams.CASES_WITH_AN_OUTCOME.toString(), globalStatistics.getCasesWithAnOutcome());
                put(GlobalParams.RECOVERED_COUNT.toString(), globalStatistics.getCasesWithAnOutcome());
                put(GlobalParams.RECOVERED_PERCENTAGE.toString(), globalStatistics.getRecoveredPercentage());
                put(GlobalParams.DEATH_COUNT.toString(), globalStatistics.getDeathCount());
                put(GlobalParams.DEATH_PERCENTAGE.toString(), globalStatistics.getDeathPercentage());
            }
        };
        createDataRows(globalSheet, Arrays.asList(dataMap));
    }

    private static void createCountryWiseData(XSSFSheet countryWiseSheet, GlobalStatistics globalStatistics) {
        CountryLevelCovid19Params params[] = CountryLevelCovid19Params.values();
        createHeaderRow(countryWiseSheet, Arrays.asList(params)
                                                .stream()
                                                .map(param -> param.getName())
                                                .collect(Collectors.toList()));

        createDataRows(countryWiseSheet, globalStatistics.getCountryWiseCoronaDetails());
    }

    private static void createHeaderRow(XSSFSheet sheet, List<String> headerNames) {
        Row header = sheet.createRow(HEADER_ROW_NUMBER);
        for (int cellCount = 0; cellCount < headerNames.size(); cellCount++) {
            header.createCell(cellCount)
                  .setCellValue(headerNames.get(cellCount));
        }
    }

    private static void createDataRows(XSSFSheet sheet, List<Map<String, String>> data) {
        for (int rowCount = DATA_ROW_START_NUMBER; rowCount <= data.size(); rowCount++) {
            Row dataRow = sheet.createRow(rowCount);
            Map<String, String> dataMap = data.get(rowCount - 1);
            int cellCount = 0;
            for (String value : dataMap.values()) {
                dataRow.createCell(cellCount)
                       .setCellValue(value);
                cellCount++;
            }
        }
    }
}
