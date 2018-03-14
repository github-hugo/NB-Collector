package com.nhb.NBIot.cloud.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.nhb.NBIot.entity.DeviceInfo;
import com.nhb.NBIot.utils.HttpsUtil;
import com.nhb.NBIot.utils.JsonUtil;
import com.nhb.NBIot.utils.StreamClosedHttpResponse;

@Service
public class CloudDeviceManageService {

	HttpsUtil httpsUtil;

	/**
	 * Register Directly Connected Device 注册直连设备
	 * 
	 * @throws Exception
	 */
	public DeviceInfo registerDirectlyConnectedDevice(String url, Map<String, String> param, Map<String, String> header)
			throws Exception {
		// Two-Way Authentication
		httpsUtil = new HttpsUtil();

		String jsonRequest = JsonUtil.jsonObj2Sting(param);
		StreamClosedHttpResponse responseReg = httpsUtil.doPostJsonGetStatusLine(url, header, jsonRequest);
		DeviceInfo deviceInfo = JsonUtil.convertJsonStringToObject(responseReg.getContent(), DeviceInfo.class);
		return deviceInfo;

	}

	/**
	 * 修改设备信息
	 * 
	 * @param url
	 * @param param
	 * @param header
	 * @return
	 */
	public boolean modifyDeviceInfo(String url, Map<String, String> header, Map<String, Object> param) {
		httpsUtil = new HttpsUtil();
		String jsonRequest = JsonUtil.jsonObj2Sting(param);
		StreamClosedHttpResponse response = httpsUtil.doPutJsonGetStatusLine(url, header, jsonRequest);
		if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 204) {
			return true;
		}
		return false;
	}

	/**
	 * 删除直连设备
	 * 
	 * @param url
	 * @param header
	 * @return
	 */
	public boolean deleteDirectlyConnectedDevice(String url, Map<String, String> header) {
		httpsUtil = new HttpsUtil();
		StreamClosedHttpResponse response = httpsUtil.doDeleteGetStatusLine(url, header);
		if (response.getStatusLine().getStatusCode() == 204) {
			return true;
		}
		return false;
	}

}
