package com.joydeep.selenium.corona.scrapper.daos;

import com.joydeep.selenium.corona.scrapper.domains.GlobalStatistics;
import com.joydeep.selenium.corona.scrapper.repositories.GlobalStatisticsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MongoOperationsDao implements PersistenceDao<GlobalStatistics> {
    private final Logger logger = LoggerFactory.getLogger(PersistenceDao.class);
    private final GlobalStatisticsRepository globalStatisticsRepository;

    public MongoOperationsDao(GlobalStatisticsRepository globalStatisticsRepository) {
        this.globalStatisticsRepository = globalStatisticsRepository;
    }

    @Override
    public void persist(GlobalStatistics globalStatistics) {
        globalStatisticsRepository.save(globalStatistics);
        logger.info("persisted successfully");
    }
}
