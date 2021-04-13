package com.joydeep.selenium.corona.scrapper.services;

public interface PersistenceService<T> {
    public void persist(T t);
}
