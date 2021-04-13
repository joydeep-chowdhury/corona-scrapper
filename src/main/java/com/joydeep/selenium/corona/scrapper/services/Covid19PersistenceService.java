package com.joydeep.selenium.corona.scrapper.services;

import com.joydeep.selenium.corona.scrapper.daos.PersistenceDao;
import com.joydeep.selenium.corona.scrapper.domains.GlobalStatistics;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("covid19persistor")
public class Covid19PersistenceService implements PersistenceService<GlobalStatistics> {
    private PersistenceDao persistenceDao;

    public Covid19PersistenceService(PersistenceDao persistenceDao) {
        this.persistenceDao = persistenceDao;
    }

    @Override
    public void persist(GlobalStatistics globalStatistics) {
        persistenceDao.persist(globalStatistics);
    }
}
