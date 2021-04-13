package com.joydeep.selenium.corona.scrapper.domains;

public enum CountryLevelCovid19Params {
    SERIAL_NO("Serial No"),
    COUNTRY_NAME("Country Name"),
    TOTAL_CASES("Total Cases"),
    NEW_CASES("New Cases"),
    TOTAL_DEATHS("Total Deaths"),
    NEW_DEATHS("New Deaths"),
    TOTAL_RECOVERED("Total Recovered"),
    ACTIVE_CASES("Active Cases"),
    SERIOUS_CRITICAL("Serious/Critical"),
    TOTAL_CASES_PER_ONE_MILLION_POPULATION("Total Cases/ 1 Million Population"),
    DEATHS_PER_ONE_MILLION_POPULATION("Deaths/ 1 Million Population"),
    TOTAL_TESTS("Total Tests"),
    TESTS_PER_ONE_MILLION_POPULATION("Tests/ 1 Million Population"),
    POPULATION("Population")
    ;

    private String name;

    public String getName() {
        return name;
    }

    CountryLevelCovid19Params(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
