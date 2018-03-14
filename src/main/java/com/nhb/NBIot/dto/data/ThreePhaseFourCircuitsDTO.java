package com.nhb.NBIot.dto.data;

public class ThreePhaseFourCircuitsDTO {

	// 时间
	private String meterTime;
	// 频率
	private double frequency;
	// 电压
	private double aVolt;
	private double bVolt;
	private double cVolt;
	// 电流
	private double aCurrent;
	private double bCurrent;
	private double cCurrent;
	// 有功功率
	private double kw;
	private double aKw;
	private double bKw;
	private double cKw;
	// 无功功率
	private double kvar;
	// 视在功率
	private double kva;
	// 功率因数
	private double powerFactor;
	// 组合有功电能
	private double kwhTotal;
	private double aKwh;
	private double bKwh;
	private double cKwh;
	// 无功电能
	private double kvarh;

	public String getMeterTime() {
		return meterTime;
	}

	public void setMeterTime(String meterTime) {
		this.meterTime = meterTime;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public double getaVolt() {
		return aVolt;
	}

	public void setaVolt(double aVolt) {
		this.aVolt = aVolt;
	}

	public double getbVolt() {
		return bVolt;
	}

	public void setbVolt(double bVolt) {
		this.bVolt = bVolt;
	}

	public double getcVolt() {
		return cVolt;
	}

	public void setcVolt(double cVolt) {
		this.cVolt = cVolt;
	}

	public double getaCurrent() {
		return aCurrent;
	}

	public void setaCurrent(double aCurrent) {
		this.aCurrent = aCurrent;
	}

	public double getbCurrent() {
		return bCurrent;
	}

	public void setbCurrent(double bCurrent) {
		this.bCurrent = bCurrent;
	}

	public double getcCurrent() {
		return cCurrent;
	}

	public void setcCurrent(double cCurrent) {
		this.cCurrent = cCurrent;
	}

	public double getKw() {
		return kw;
	}

	public void setKw(double kw) {
		this.kw = kw;
	}

	public double getaKw() {
		return aKw;
	}

	public void setaKw(double aKw) {
		this.aKw = aKw;
	}

	public double getbKw() {
		return bKw;
	}

	public void setbKw(double bKw) {
		this.bKw = bKw;
	}

	public double getcKw() {
		return cKw;
	}

	public void setcKw(double cKw) {
		this.cKw = cKw;
	}

	public double getKvar() {
		return kvar;
	}

	public void setKvar(double kvar) {
		this.kvar = kvar;
	}

	public double getKva() {
		return kva;
	}

	public void setKva(double kva) {
		this.kva = kva;
	}

	public double getPowerFactor() {
		return powerFactor;
	}

	public void setPowerFactor(double powerFactor) {
		this.powerFactor = powerFactor;
	}

	public double getKwhTotal() {
		return kwhTotal;
	}

	public void setKwhTotal(double kwhTotal) {
		this.kwhTotal = kwhTotal;
	}

	public double getaKwh() {
		return aKwh;
	}

	public void setaKwh(double aKwh) {
		this.aKwh = aKwh;
	}

	public double getbKwh() {
		return bKwh;
	}

	public void setbKwh(double bKwh) {
		this.bKwh = bKwh;
	}

	public double getcKwh() {
		return cKwh;
	}

	public void setcKwh(double cKwh) {
		this.cKwh = cKwh;
	}

	public double getKvarh() {
		return kvarh;
	}

	public void setKvarh(double kvarh) {
		this.kvarh = kvarh;
	}

}
