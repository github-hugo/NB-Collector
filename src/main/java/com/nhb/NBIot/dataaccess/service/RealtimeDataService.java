package com.nhb.NBIot.dataaccess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhb.NBIot.dataaccess.dao.RealtimeDataDao;
import com.nhb.NBIot.entity.RealtimeData;

@Service
public class RealtimeDataService {
	
	@Autowired
	private RealtimeDataDao realtimeDataDao;
	
	public RealtimeData findByDeviceId(String deviceId){
		return realtimeDataDao.findByDeviceId(deviceId);
	}
	
	public RealtimeData save(RealtimeData realtimeData){
		return realtimeDataDao.save(realtimeData);
	}
	
}
