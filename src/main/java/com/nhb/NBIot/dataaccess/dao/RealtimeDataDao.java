package com.nhb.NBIot.dataaccess.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nhb.NBIot.entity.RealtimeData;

@Repository
public interface RealtimeDataDao extends MongoRepository<RealtimeData, String> {

	RealtimeData findByDeviceId(String deviceId);

}
