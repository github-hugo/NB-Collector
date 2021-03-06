package com.nhb.NBIot.dataaccess.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nhb.NBIot.entity.HistoryData;

@Repository
public interface HistoryDataDao extends MongoRepository<HistoryData, String> {

}
