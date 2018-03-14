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
public class QueryDevicesRequest {

	@XmlElement(name = "appId")
	@ApiModelProperty(value = "appId", required = true, example = "8hPQ3lYoNLBDMf_VydJPaxWbkoAa")
	private String appId;

	@XmlElement(name = "gatewayId")
	@ApiModelProperty(value = "gatewayId", required = true, example = "813a76b0-abb6-4131-bedc-49297377fc1b")
	private String gatewayId;

	@XmlElement(name = "nodeType")
	@ApiModelProperty(value = "nodeType", required = true, example = "GATEWAY")
	private String nodeType;

	@XmlElement(name = "deviceType")
	@ApiModelProperty(value = "deviceType", required = true, example = "ThreePhaseSingleCircuit")
	private String deviceType;

	@XmlElement(name = "protocolType")
	@ApiModelProperty(value = "protocolType", required = true, example = "CoAP")
	private String protocolType;

	@XmlElement(name = "pageNo")
	@ApiModelProperty(value = "pageNo", required = true, example = "1")
	private Integer pageNo;

	@XmlElement(name = "pageSize")
	@ApiModelProperty(value = "pageSize", required = true, example = "10")
	private Integer pageSize;

	@XmlElement(name = "status")
	@ApiModelProperty(value = "status", required = true, example = "ONLINE")
	private String status;

	@XmlElement(name = "startTime")
	@ApiModelProperty(value = "startTime", required = true, example = "20171222T004752Z")
	private String startTime;

	@XmlElement(name = "endTime")
	@ApiModelProperty(value = "endTime", required = true, example = "20171230T004752Z")
	private String endTime;

	@XmlElement(name = "sort")
	@ApiModelProperty(value = "sort", required = true, example = "DESC")
	private String sort;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
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

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
