package com.joydeep.selenium.corona.scrapper.utilities;

import com.joydeep.selenium.corona.scrapper.exceptions.KeyNotFoundException;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public class ScrapingUtilities {

    public static String scrapeMainCounterValues(Elements elements, String keyName) {
        Optional<Element> optionalElement = elements.stream()
                                                    .filter(element -> element.select("h1")
                                                                              .first()
                                                                              .text()
                                                                              .equals(keyName))
                                                    .findFirst();
        if (!optionalElement.isPresent()) {
            throw new KeyNotFoundException("Key " + keyName + " not found in main counter section");
        }
        Element filteredElement = optionalElement.get();
        return filteredElement.select("div[class=maincounter-number]")
                              .first()
                              .text();
    }

    public static String getCurrentlyInfectedPatients(Elements elements, String keyName) {
        return scrapePanelElementNumberTableMainValue(elements, keyName);
    }

    public static String getCasesWithAnOutcome(Elements elements, String keyName) {
        return scrapePanelElementNumberTableMainValue(elements, keyName);
    }

    public static String getInMildCondition(Elements elements, String keyName) {
        return scrapeNumberTableFirstDivSpan(elements, keyName);
    }

    public static String getInMildConditionPercentage(Elements elements, String keyName) {
        return scrapeNumberTableFirstDivStrong(elements, keyName) + " %";
    }

    public static String getInSeriousCondition(Elements elements, String keyName) {
        return scrapeNumberTableSecondDivSpan(elements, keyName);
    }

    public static String getInSeriousConditionPercentage(Elements elements, String keyName) {
        return scrapeNumberTableSecondDivStrong(elements, keyName) + " %";
    }

    public static String getRecoveredCount(Elements elements, String keyName) {
        return scrapeNumberTableFirstDivSpan(elements, keyName);
    }

    public static String getRecoveredPercentage(Elements elements, String keyName) {
        return scrapeNumberTableFirstDivStrong(elements, keyName) + " %";
    }

    public static String getDeathCount(Elements elements, String keyName) {
        return scrapeNumberTableSecondDivSpan(elements, keyName);
    }

    public static String getDeathPercentage(Elements elements, String keyName) {
        return scrapeNumberTableSecondDivStrong(elements, keyName) + " %";
    }

    public static Map<Integer, String> scrapeTableHeaderMap(Document document) {
        Element countriesTable = document.select("table[id=main_table_countries_today]")
                                         .first();
        return countriesTable.select("thead")
                             .first()
                             .select("tr")
                             .first()
                             .select("th")
                             .stream()
                             .filter(column -> !isDisplayNone(column))
                             .collect(Collectors.toMap(Element::elementSiblingIndex, Element::text));
    }

    public static List<Map<String, String>> scrapeCountryWiseCoronaDetails(Document document, Map<Integer, String> headerIndexNameMap) {
        Element countriesTable = document.select("table[id=main_table_countries_today]")
                                         .first();
        List<Element> validRows = countriesTable.select("tbody")
                                                .first()
                                                .select("tr")
                                                .stream()
                                                .filter(row -> !isDisplayNone(row))
                                                .collect(Collectors.toList());
        List<Map<String, String>> countryList = new ArrayList<Map<String, String>>();
        validRows.forEach(country -> {
            List<Element> countryColumnList = country.select("td")
                                                     .stream()
                                                     .collect(Collectors.toList());
            Map<String, String> countryNameValueMap = new LinkedHashMap<String, String>();
            for (Entry<Integer, String> entry : headerIndexNameMap.entrySet()) {
                int columnIndex = entry.getKey();
                String columnName = entry.getValue();
                String columnValue = countryColumnList.get(columnIndex)
                                                      .text();
                countryNameValueMap.put(columnName,columnValue);
            }
            countryList.add(countryNameValueMap);
        });
        return countryList;
    }

    private static boolean isDisplayNone(Element element) {
        Optional<Attribute> optionalStyleAttribute = element.attributes()
                                                            .asList()
                                                            .stream()
                                                            .filter(attribute -> attribute.getKey()
                                                                                          .trim()
                                                                                          .equals("style"))
                                                            .findFirst();
        if (optionalStyleAttribute.isPresent()) {
            Attribute styleAttribute = optionalStyleAttribute.get();
            if (styleAttribute.getValue()
                              .contains("display: none")) {
                return true;
            }
        }
        return false;
    }

    private static String scrapePanelElementNumberTableMainValue(Elements elements, String keyName) {
        Optional<Element> optionalElement = elements.stream()
                                                    .filter(element -> element.select("div[class=panel-heading]")
                                                                              .first()
                                                                              .text()
                                                                              .equals(keyName))
                                                    .findFirst();
        if (!optionalElement.isPresent()) {
            throw new KeyNotFoundException("Key " + keyName + " not found in panel header section");
        }
        return optionalElement.get()
                              .select("div[class=number-table-main]")
                              .first()
                              .text();
    }

    private static String scrapeNumberTableFirstDivSpan(Elements elements, String keyName) {
        Optional<Element> optionalElement = elements.stream()
                                                    .filter(element -> element.select("div[class=panel-heading]")
                                                                              .first()
                                                                              .text()
                                                                              .equals(keyName))
                                                    .findFirst();
        if (!optionalElement.isPresent()) {
            throw new KeyNotFoundException("Key " + keyName + " not found in panel header section");
        }
        return optionalElement.get()
                              .select("div[class=number-table-main]")
                              .first()
                              .parent()
                              .child(2)
                              .child(0)
                              .select("span")
                              .first()
                              .text();
    }

    private static String scrapeNumberTableFirstDivStrong(Elements elements, String keyName) {
        Optional<Element> optionalElement = elements.stream()
                                                    .filter(element -> element.select("div[class=panel-heading]")
                                                                              .first()
                                                                              .text()
                                                                              .equals(keyName))
                                                    .findFirst();
        if (!optionalElement.isPresent()) {
            throw new KeyNotFoundException("Key " + keyName + " not found in panel header section");
        }
        return optionalElement.get()
                              .select("div[class=number-table-main]")
                              .first()
                              .parent()
                              .child(2)
                              .child(0)
                              .select("strong")
                              .first()
                              .text();
    }

    private static String scrapeNumberTableSecondDivSpan(Elements elements, String keyName) {
        Optional<Element> optionalElement = elements.stream()
                                                    .filter(element -> element.select("div[class=panel-heading]")
                                                                              .first()
                                                                              .text()
                                                                              .equals(keyName))
                                                    .findFirst();
        if (!optionalElement.isPresent()) {
            throw new KeyNotFoundException("Key " + keyName + " not found in panel header section");
        }
        return optionalElement.get()
                              .select("div[class=number-table-main]")
                              .first()
                              .parent()
                              .child(2)
                              .child(1)
                              .select("span")
                              .first()
                              .text();
    }

    private static String scrapeNumberTableSecondDivStrong(Elements elements, String keyName) {
        Optional<Element> optionalElement = elements.stream()
                                                    .filter(element -> element.select("div[class=panel-heading]")
                                                                              .first()
                                                                              .text()
                                                                              .equals(keyName))
                                                    .findFirst();
        if (!optionalElement.isPresent()) {
            throw new KeyNotFoundException("Key " + keyName + " not found in panel header section");
        }
        return optionalElement.get()
                              .select("div[class=number-table-main]")
                              .first()
                              .parent()
                              .child(2)
                              .child(1)
                              .select("strong")
                              .first()
                              .text();
    }
}
