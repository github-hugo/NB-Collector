package com.nhb.NBIot.dataaccess.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhb.NBIot.dataaccess.dao.DeviceCircuitDao;
import com.nhb.NBIot.entity.DeviceCircuit;

@Service
public class DeviceCircuitService {

	@Autowired
	private DeviceCircuitDao deviceCircuitDao;

	public DeviceCircuit save(DeviceCircuit circuit) {
		return deviceCircuitDao.save(circuit);
	}

	public DeviceCircuit findById(String circuitId) {
		return deviceCircuitDao.findOne(circuitId);
	}

	public List<DeviceCircuit> findByDeviceId(String deviceId) {
		return deviceCircuitDao.findByDeviceId(deviceId);
	}

	public void deleteById(String circuitId) {
		deviceCircuitDao.delete(circuitId);
	}

}
