package com.nhb.NBIot.cloud.service;

import java.util.Map;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import com.nhb.NBIot.dto.data.DataHistory;
import com.nhb.NBIot.dto.data.GetDeviceRspDTO;
import com.nhb.NBIot.dto.data.QueryDevicesDTO;
import com.nhb.NBIot.utils.HttpsUtil;
import com.nhb.NBIot.utils.JsonUtil;
import com.nhb.NBIot.utils.StreamClosedHttpResponse;

@Service
public class CloudDataCollectorService {

	HttpsUtil httpsUtil;

	/**
	 * 根据条件批量查询设备信息
	 * 
	 * @param url
	 * @param param
	 * @param header
	 * @return QueryDevicesDTO
	 * @throws Exception
	 */
	public QueryDevicesDTO queryDevices(String url, Map<String, String> param, Map<String, String> header)
			throws Exception {
		httpsUtil = new HttpsUtil();
		StreamClosedHttpResponse bodyQueryDevices = httpsUtil.doGetWithParasGetStatusLine(url, param, header);
		QueryDevicesDTO devicesDTO = JsonUtil.convertJsonStringToObject(bodyQueryDevices.getContent(),
				QueryDevicesDTO.class);
		return devicesDTO;
	}

	/**
	 * 查询单个设备
	 * 
	 * @param url
	 * @param param
	 * @param header
	 * @return GetDeviceRspDTO
	 * @throws Exception
	 */
	public GetDeviceRspDTO queryDevice(String url, Map<String, String> param, Map<String, String> header)
			throws Exception {
		httpsUtil = new HttpsUtil();
		StreamClosedHttpResponse bodyQueryDeviceData = httpsUtil.doGetWithParasGetStatusLine(url, param, header);
		GetDeviceRspDTO deviceRspDTO = JsonUtil.convertJsonStringToObject(bodyQueryDeviceData.getContent(),
				GetDeviceRspDTO.class);
		return deviceRspDTO;
	}

	/**
	 * 订阅平台数据
	 * 
	 * @param url
	 * @param param
	 * @param header
	 * @return boolean
	 */
	public boolean subscribeNotification(String url, Map<String, String> param, Map<String, String> header) {
		httpsUtil = new HttpsUtil();
		String jsonRequest = JsonUtil.jsonObj2Sting(param);
		HttpResponse httpResponse = httpsUtil.doPostJson(url, header, jsonRequest);
		if (httpResponse.getStatusLine().getStatusCode() == 201) {
			return true;
		}
		return false;
	}

	/**
	 * 查询设备的历史数据
	 * 
	 * @param url
	 * @param param
	 * @param header
	 * @return DataHistory
	 * @throws Exception
	 */
	public DataHistory queryDeviceDataHistory(String url, Map<String, String> param, Map<String, String> header)
			throws Exception {
		httpsUtil = new HttpsUtil();
		StreamClosedHttpResponse bodyQueryDeviceHistoryData = httpsUtil.doGetWithParasGetStatusLine(url, param, header);
		DataHistory dataHistory = JsonUtil.convertJsonStringToObject(bodyQueryDeviceHistoryData.getContent(),
				DataHistory.class);
		return dataHistory;
	}

}
