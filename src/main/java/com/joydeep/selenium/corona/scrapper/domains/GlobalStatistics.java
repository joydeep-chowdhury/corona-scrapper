package com.joydeep.selenium.corona.scrapper.domains;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "global_covid19_statistics")
public class GlobalStatistics {
    @Id
    private String _id;
    private final String totalCoronavirusCases;
    private final String totalCoronavirusDeaths;
    private final String totalCoronavirusRecovered;
    private String currentlyInfectedPatients;
    private String inMildCondition;
    private String inMildConditionPercentage;
    private String inSeriousCondition;
    private String inSeriousConditionPercentage;
    private String casesWithAnOutcome;
    private String recoveredCount;
    private String recoveredPercentage;
    private String deathCount;
    private String deathPercentage;
    private List<Map<String, String>> countryWiseCoronaDetails;

    private GlobalStatistics(GlobalStatisticsBuilder globalStatisticsBuilder) {
        this.totalCoronavirusCases = globalStatisticsBuilder.totalCoronavirusCases;
        this.totalCoronavirusDeaths = globalStatisticsBuilder.totalCoronavirusDeaths;
        this.totalCoronavirusRecovered = globalStatisticsBuilder.totalCoronavirusRecovered;
        this.currentlyInfectedPatients = globalStatisticsBuilder.currentlyInfectedPatients;
        this.inMildCondition = globalStatisticsBuilder.inMildCondition;
        this.inMildConditionPercentage = globalStatisticsBuilder.inMildConditionPercentage;
        this.inSeriousCondition = globalStatisticsBuilder.inSeriousCondition;
        this.inSeriousConditionPercentage = globalStatisticsBuilder.inSeriousConditionPercentage;
        this.casesWithAnOutcome = globalStatisticsBuilder.casesWithAnOutcome;
        this.recoveredCount = globalStatisticsBuilder.recoveredCount;
        this.recoveredPercentage = globalStatisticsBuilder.recoveredPercentage;
        this.deathCount = globalStatisticsBuilder.deathCount;
        this.deathPercentage = globalStatisticsBuilder.deathPercentage;
        this.countryWiseCoronaDetails = globalStatisticsBuilder.countryWiseCoronaDetails;
    }

    public String get_id() {
        return _id;
    }

    public String getTotalCoronavirusCases() {
        return totalCoronavirusCases;
    }

    public String getTotalCoronavirusDeaths() {
        return totalCoronavirusDeaths;
    }

    public String getTotalCoronavirusRecovered() {
        return totalCoronavirusRecovered;
    }

    public String getCurrentlyInfectedPatients() {
        return currentlyInfectedPatients;
    }

    public String getInMildCondition() {
        return inMildCondition;
    }

    public String getInMildConditionPercentage() {
        return inMildConditionPercentage;
    }

    public String getInSeriousCondition() {
        return inSeriousCondition;
    }

    public String getInSeriousConditionPercentage() {
        return inSeriousConditionPercentage;
    }

    public String getCasesWithAnOutcome() {
        return casesWithAnOutcome;
    }

    public String getRecoveredCount() {
        return recoveredCount;
    }

    public String getRecoveredPercentage() {
        return recoveredPercentage;
    }

    public String getDeathCount() {
        return deathCount;
    }

    public String getDeathPercentage() {
        return deathPercentage;
    }

    public List<Map<String, String>> getCountryWiseCoronaDetails() {
        return countryWiseCoronaDetails;
    }

    @Override
    public String toString() {
        return "GlobalStatistics{" + "totalCoronavirusCases='" + totalCoronavirusCases + '\'' + ", totalCoronavirusDeaths='" + totalCoronavirusDeaths + '\''
                + ", totalCoronavirusRecovered='" + totalCoronavirusRecovered + '\'' + ", currentlyInfectedPatients='" + currentlyInfectedPatients + '\'' + ", inMildCondition='"
                + inMildCondition + '\'' + ", inMildConditionPercentage='" + inMildConditionPercentage + '\'' + ", inSeriousCondition='" + inSeriousCondition + '\''
                + ", inSeriousConditionPercentage='" + inSeriousConditionPercentage + '\'' + ", casesWithAnOutcome='" + casesWithAnOutcome + '\'' + ", recoveredCount='"
                + recoveredCount + '\'' + ", recoveredPercentage='" + recoveredPercentage + '\'' + ", deathCount='" + deathCount + '\'' + ", deathPercentage='" + deathPercentage
                + '\'' + ", countryWiseCoronaDetails=" + countryWiseCoronaDetails + '}';
    }

    public static class GlobalStatisticsBuilder {
        private final String totalCoronavirusCases;
        private final String totalCoronavirusDeaths;
        private final String totalCoronavirusRecovered;
        private String currentlyInfectedPatients;
        private String inMildCondition;
        private String inMildConditionPercentage;
        private String inSeriousCondition;
        private String inSeriousConditionPercentage;
        private String casesWithAnOutcome;
        private String recoveredCount;
        private String recoveredPercentage;
        private String deathCount;
        private String deathPercentage;
        private List<Map<String, String>> countryWiseCoronaDetails;

        public GlobalStatisticsBuilder(final String totalCoronavirusCases, final String totalCoronavirusDeaths, final String totalCoronavirusRecovered) {
            this.totalCoronavirusCases = totalCoronavirusCases;
            this.totalCoronavirusDeaths = totalCoronavirusDeaths;
            this.totalCoronavirusRecovered = totalCoronavirusRecovered;
        }

        public GlobalStatisticsBuilder currentlyInfectedPatients(String currentlyInfectedPatients) {
            this.currentlyInfectedPatients = currentlyInfectedPatients;
            return this;
        }

        public GlobalStatisticsBuilder inMildCondition(String inMildCondition) {
            this.inMildCondition = inMildCondition;
            return this;
        }

        public GlobalStatisticsBuilder inMildConditionPercentage(String inMildConditionPercentage) {
            this.inMildConditionPercentage = inMildConditionPercentage;
            return this;
        }

        public GlobalStatisticsBuilder inSeriousCondition(String inSeriousCondition) {
            this.inSeriousCondition = inSeriousCondition;
            return this;
        }

        public GlobalStatisticsBuilder inSeriousConditionPercentage(String inSeriousConditionPercentage) {
            this.inSeriousConditionPercentage = inSeriousConditionPercentage;
            return this;
        }

        public GlobalStatisticsBuilder casesWithAnOutcome(String casesWithAnOutcome) {
            this.casesWithAnOutcome = casesWithAnOutcome;
            return this;
        }

        public GlobalStatisticsBuilder recoveredCount(String recoveredCount) {
            this.recoveredCount = recoveredCount;
            return this;
        }

        public GlobalStatisticsBuilder recoveredPercentage(String recoveredPercentage) {
            this.recoveredPercentage = recoveredPercentage;
            return this;
        }

        public GlobalStatisticsBuilder deathCount(String deathCount) {
            this.deathCount = deathCount;
            return this;
        }

        public GlobalStatisticsBuilder deathPercentage(String deathPercentage) {
            this.deathPercentage = deathPercentage;
            return this;
        }

        public GlobalStatisticsBuilder countryWiseCoronaDetails(List<Map<String, String>> countryWiseCoronaDetails) {
            this.countryWiseCoronaDetails = countryWiseCoronaDetails;
            return this;
        }

        public GlobalStatistics build() {
            return new GlobalStatistics(this);
        }
    }
}
