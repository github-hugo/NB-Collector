package com.nhb.NBIot.dataaccess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhb.NBIot.dataaccess.dao.DeviceInfoDao;
import com.nhb.NBIot.entity.DeviceInfo;

@Service("deviceInfoService")
public class DeviceInfoService {

	@Autowired
	private DeviceInfoDao deviceInfoDao;

	public DeviceInfo save(DeviceInfo deviceInfo) {
		return deviceInfoDao.save(deviceInfo);
	}

	public DeviceInfo findById(String nodeId) {
		DeviceInfo info = deviceInfoDao.findOne(nodeId);
		return info;
	}

	public void delete(DeviceInfo deviceInfo) {
		deviceInfoDao.delete(deviceInfo);
	}
}
