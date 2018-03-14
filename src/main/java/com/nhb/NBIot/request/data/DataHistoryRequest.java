package com.nhb.NBIot.request.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel
public class DataHistoryRequest {

	@XmlElement(name = "deviceId")
	@ApiModelProperty(value = "deviceId", required = true, example = "813a76b0-abb6-4131-bedc-49297377fc1b")
	private String deviceId;

	@XmlElement(name = "gatewayId")
	@ApiModelProperty(value = "gatewayId", required = true, example = "813a76b0-abb6-4131-bedc-49297377fc1b")
	private String gatewayId;

	@XmlElement(name = "serviceId")
	@ApiModelProperty(value = "serviceId", required = true, example = "ThreePhaseSingleCircuit")
	private String serviceId;

	@XmlElement(name = "property")
	@ApiModelProperty(value = "property", required = true, example = "")
	private String property;

	@XmlElement(name = "appId")
	@ApiModelProperty(value = "appId", required = true, example = "8hPQ3lYoNLBDMf_VydJPaxWbkoAa")
	private String appId;

	@XmlElement(name = "pageNo")
	@ApiModelProperty(value = "pageNo", required = true, example = "1")
	private Integer pageNo;

	@XmlElement(name = "pageSize")
	@ApiModelProperty(value = "pageSize", required = true, example = "10")
	private Integer pageSize;

	@XmlElement(name = "startTime")
	@ApiModelProperty(value = "startTime", required = true, example = "20171222T004752Z")
	private String startTime;

	@XmlElement(name = "endTime")
	@ApiModelProperty(value = "endTime", required = true, example = "20171230T004752Z")
	private String endTime;

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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
