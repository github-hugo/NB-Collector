package com.nhb.NBIot.dataaccess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhb.NBIot.dataaccess.dao.HistoryDataDao;
import com.nhb.NBIot.entity.HistoryData;

@Service
public class HistoryDataService {

	@Autowired
	private HistoryDataDao dataElectricityDao;

	public HistoryData save(HistoryData historyData) {
		return dataElectricityDao.save(historyData);
	}

}
