package com.joydeep.selenium.corona.scrapper.configurations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class ChromeDriverConfiguration {

    @Bean
    public WebDriver webDriver(SeleniumConfiguration seleniumConfiguration) {
        System.setProperty(seleniumConfiguration.getWebDriverType(), seleniumConfiguration.getWebDriverPath());
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage()
                 .deleteAllCookies();
        webDriver.manage()
                 .window()
                 .maximize();
        webDriver.manage()
                 .timeouts()
                 .pageLoadTimeout(seleniumConfiguration.getWebDriverPageLoadTimeout(), TimeUnit.SECONDS);
        return webDriver;
    }
}
