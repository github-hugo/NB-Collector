package com.nhb.NBIot.dto.data;

import java.util.List;

import com.nhb.NBIot.dto.data.GetDeviceRspDTO;
import com.nhb.NBIot.dto.util.PageUtil;

public class QueryDevicesDTO extends PageUtil {

	private List<GetDeviceRspDTO> devices;

	public List<GetDeviceRspDTO> getDevices() {
		return devices;
	}

	public void setDevices(List<GetDeviceRspDTO> devices) {
		this.devices = devices;
	}

}
