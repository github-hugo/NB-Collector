package com.nhb.NBIot.dto.data;

import com.nhb.NBIot.dto.device.DeviceService;

public class DeviceDataChangedDTO {

	private String notifyType;

	private String requestId;

	private String deviceId;

	private String gatewayId;

	private DeviceService service;

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public DeviceService getService() {
		return service;
	}

	public void setService(DeviceService service) {
		this.service = service;
	}

}
