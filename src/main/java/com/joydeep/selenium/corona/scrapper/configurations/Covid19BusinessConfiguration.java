package com.joydeep.selenium.corona.scrapper.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

@Configuration
@ConfigurationProperties(prefix = "covid19")
public class Covid19BusinessConfiguration {
    @NotBlank
    private String totalCoronavirusCasesKey;
    @NotBlank
    private String totalCoronavirusDeathsKey;
    @NotBlank
    private String totalCoronavirusRecoveredKey;
    @NotBlank
    private String activeCasesPanelHeadingKey;
    @NotBlank
    private String closedCasesPanelHeadingKey;
    @NotBlank
    private String worldometersSite;
    @NotBlank
    private String reportPath;
    @NotBlank
    private String reportFileName;

    public String getTotalCoronavirusCasesKey() {
        return totalCoronavirusCasesKey;
    }

    public void setTotalCoronavirusCasesKey(String totalCoronavirusCasesKey) {
        this.totalCoronavirusCasesKey = totalCoronavirusCasesKey;
    }

    public String getTotalCoronavirusDeathsKey() {
        return totalCoronavirusDeathsKey;
    }

    public void setTotalCoronavirusDeathsKey(String totalCoronavirusDeathsKey) {
        this.totalCoronavirusDeathsKey = totalCoronavirusDeathsKey;
    }

    public String getTotalCoronavirusRecoveredKey() {
        return totalCoronavirusRecoveredKey;
    }

    public void setTotalCoronavirusRecoveredKey(String totalCoronavirusRecoveredKey) {
        this.totalCoronavirusRecoveredKey = totalCoronavirusRecoveredKey;
    }

    public String getActiveCasesPanelHeadingKey() {
        return activeCasesPanelHeadingKey;
    }

    public void setActiveCasesPanelHeadingKey(String activeCasesPanelHeadingKey) {
        this.activeCasesPanelHeadingKey = activeCasesPanelHeadingKey;
    }

    public String getClosedCasesPanelHeadingKey() {
        return closedCasesPanelHeadingKey;
    }

    public void setClosedCasesPanelHeadingKey(String closedCasesPanelHeadingKey) {
        this.closedCasesPanelHeadingKey = closedCasesPanelHeadingKey;
    }

    public String getWorldometersSite() {
        return worldometersSite;
    }

    public void setWorldometersSite(String worldometersSite) {
        this.worldometersSite = worldometersSite;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }
}
