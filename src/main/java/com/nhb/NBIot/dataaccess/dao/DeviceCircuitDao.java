package com.nhb.NBIot.dataaccess.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nhb.NBIot.entity.DeviceCircuit;

@Repository
public interface DeviceCircuitDao extends MongoRepository<DeviceCircuit, String> {

	List<DeviceCircuit> findByDeviceId(String deviceId);

}
