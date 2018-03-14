package com.nhb.NBIot.dto.device;

import com.nhb.NBIot.entity.DeviceInfo;

public class DeviceAddedDTO {

	private String notifyType;

	private String deviceId;

//	private String resultCode;

	private String gatewayId;

	private DeviceInfo deviceInfo;

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

//	public String getResultCode() {
//		return resultCode;
//	}
//
//	public void setResultCode(String resultCode) {
//		this.resultCode = resultCode;
//	}

	public String getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

}
