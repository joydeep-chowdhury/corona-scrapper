package com.joydeep.selenium.corona.scrapper.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

@Configuration
@ConfigurationProperties(prefix = "selenium")
public class SeleniumConfiguration {
    @NotBlank
    private String webDriverPath;
    @NotBlank
    private String webDriverType;
    @NotBlank
    private int webDriverPageLoadTimeout;

    public void setWebDriverPath(String webDriverPath) {
        this.webDriverPath = webDriverPath;
    }

    public void setWebDriverType(String webDriverType) {
        this.webDriverType = webDriverType;
    }

    public void setWebDriverPageLoadTimeout(int webDriverPageLoadTimeout) {
        this.webDriverPageLoadTimeout = webDriverPageLoadTimeout;
    }

    public String getWebDriverPath() {
        return webDriverPath;
    }

    public String getWebDriverType() {
        return webDriverType;
    }

    public int getWebDriverPageLoadTimeout() {
        return webDriverPageLoadTimeout;
    }
}
