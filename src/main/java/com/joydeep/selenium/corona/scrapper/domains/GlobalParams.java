package com.joydeep.selenium.corona.scrapper.domains;

public enum GlobalParams {
    TOTAL_CORONAVIRUS_CASES("Total Coronavirus Cases"),
    TOTAL_CORONAVIRUS_DEATHS("Total Coronavirus Deaths"),
    TOTAL_CORONAVIRUS_RECOVERED("Total Coronavirus Recovered"),
    CURRENTLY_INFECTED_PATIENTS("Currently Infected Patients"),
    IN_MILD_CONDITION("In Mild Condition"),
    IN_MILD_CONDITION_PERCENTAGE("In Mild Condition Percentage"),
    IN_SERIOUS_CONDITION("In Serious Condition"),
    IN_SERIOUS_CONDITION_PERCENTAGE("In Serious Condition Percentage"),
    CASES_WITH_AN_OUTCOME("Cases With An Outcome"),
    RECOVERED_COUNT("Recovered Count"),
    RECOVERED_PERCENTAGE("Recovered Percentage"),
    DEATH_COUNT("Death Count"),
    DEATH_PERCENTAGE("Death Percentage")
    ;

    private String name;

    GlobalParams(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
