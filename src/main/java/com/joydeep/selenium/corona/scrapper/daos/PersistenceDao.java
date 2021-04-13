package com.joydeep.selenium.corona.scrapper.daos;

public interface PersistenceDao<T> {
    public void persist(T t);
}
