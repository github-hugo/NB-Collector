package com.nhb.NBIot.dto.data;

/**
 * 单相单路表
 */
public class DataSinglePhaseSingleCircuitDTO {

	private String meterTime;

	private Integer voltChange;

	private Integer currentChange;

	private Double frequency;

	private Double volt;

	private Double current;

	private Double kw;

	private Double kwhTotal;

	public String getMeterTime() {
		return meterTime;
	}

	public void setMeterTime(String meterTime) {
		this.meterTime = meterTime;
	}

	public Integer getVoltChange() {
		return voltChange;
	}

	public void setVoltChange(Integer voltChange) {
		this.voltChange = voltChange;
	}

	public Integer getCurrentChange() {
		return currentChange;
	}

	public void setCurrentChange(Integer currentChange) {
		this.currentChange = currentChange;
	}

	public Double getFrequency() {
		return frequency;
	}

	public void setFrequency(Double frequency) {
		this.frequency = frequency;
	}

	public Double getVolt() {
		return volt;
	}

	public void setVolt(Double volt) {
		this.volt = volt;
	}

	public Double getCurrent() {
		return current;
	}

	public void setCurrent(Double current) {
		this.current = current;
	}

	public Double getKw() {
		return kw;
	}

	public void setKw(Double kw) {
		this.kw = kw;
	}

	public Double getKwhTotal() {
		return kwhTotal;
	}

	public void setKwhTotal(Double kwhTotal) {
		this.kwhTotal = kwhTotal;
	}

}
