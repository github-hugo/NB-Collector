package com.nhb.NBIot.dto.data;

import java.util.List;

import com.nhb.NBIot.dto.device.DeviceService;
import com.nhb.NBIot.entity.DeviceInfo;

public class GetDeviceRspDTO {

	private String deviceId;

	private String gatewayId;

	private String nodeType;

	private String createTime;

	private String lastModifiedTime;

	private DeviceInfo deviceInfo;

	private List<DeviceService> services;

	private ConnectionInfo connectionInfo;

	private List<String> devGroupIds;

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

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public List<DeviceService> getServices() {
		return services;
	}

	public void setServices(List<DeviceService> services) {
		this.services = services;
	}

	public ConnectionInfo getConnectionInfo() {
		return connectionInfo;
	}

	public void setConnectionInfo(ConnectionInfo connectionInfo) {
		this.connectionInfo = connectionInfo;
	}

	public List<String> getDevGroupIds() {
		return devGroupIds;
	}

	public void setDevGroupIds(List<String> devGroupIds) {
		this.devGroupIds = devGroupIds;
	}

}
