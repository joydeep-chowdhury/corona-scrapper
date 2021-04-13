package com.joydeep.selenium.corona.scrapper.repositories;

import com.joydeep.selenium.corona.scrapper.domains.GlobalStatistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalStatisticsRepository extends CrudRepository<GlobalStatistics, String> {
}
