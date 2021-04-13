package com.joydeep.selenium.corona.scrapper.services;

public interface ScrapperService<T> {

    public void navigate();
    public T scrape();
}
