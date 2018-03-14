package com.nhb.NBIot.dto.data;

import java.util.List;

import com.nhb.NBIot.dto.util.PageUtil;

public class DataHistory extends PageUtil {

	private List<DeviceDataHistoryDTO> deviceDataHistoryDTOs;

	public List<DeviceDataHistoryDTO> getDeviceDataHistoryDTOs() {
		return deviceDataHistoryDTOs;
	}

	public void setDeviceDataHistoryDTOs(List<DeviceDataHistoryDTO> deviceDataHistoryDTOs) {
		this.deviceDataHistoryDTOs = deviceDataHistoryDTOs;
	}

}
