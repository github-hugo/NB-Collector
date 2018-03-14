package com.nhb.NBIot.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.nhb.NBIot.cloud.service.CloudDataCollectorService;
import com.nhb.NBIot.dataaccess.service.TokenInfoService;
import com.nhb.NBIot.dto.data.DataHistory;
import com.nhb.NBIot.dto.data.GetDeviceRspDTO;
import com.nhb.NBIot.dto.data.QueryDevicesDTO;
import com.nhb.NBIot.utils.NBConstant;

@Service
public class DataCollectionService {

	@Autowired
	private CloudDataCollectorService cloudDataCollectorService;

	@Autowired
	private TokenInfoService tokenInfoService;

	String appId = NBConstant.APPID;
	String secret = NBConstant.SECRET;

	/**
	 * 查询单个设备
	 * 
	 * @return
	 * @throws Exception
	 */
	public GetDeviceRspDTO queryDevice(Map<String, String> param) throws Exception {
		String deviceId = param.get("deviceId");
		// 获取url路径
		String url = NBConstant.QUERY_DEVICE_DATA + "/" + deviceId;
		// 获取appId以及accessToken
		Map<String, String> header = getHeaderMap(appId, secret);
		return cloudDataCollectorService.queryDevice(url, param, header);
	}

	/**
	 * 查询设备列表信息
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public QueryDevicesDTO queryDevices(Map<String, String> param) throws Exception {
		String url = NBConstant.QUERY_DEVICES;
		Map<String, String> header = getHeaderMap(appId, secret);
		return cloudDataCollectorService.queryDevices(url, param, header);
	}

	public boolean subscribeNotification(Map<String, String> param) throws Exception {
		String url = NBConstant.SUBSCRIBE_NOTIFYCATION;
		Map<String, String> header = getHeaderMap(appId, secret);
		return cloudDataCollectorService.subscribeNotification(url, param, header);
	}

	/**
	 * 根据appId和secret获取accessToken头信息
	 * 
	 * @param appId
	 * @param secret
	 * @return Map<String, String>
	 */
	public Map<String, String> getHeaderMap(String appId, String secret) {
		Map<String, String> header = Maps.newHashMap();
		String accessToken = tokenInfoService.findByAppIdAndSecret(appId, secret).getAccessToken();
		header.put(NBConstant.HEADER_APP_KEY, appId);
		header.put(NBConstant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
		return header;
	}

	/**
	 * 查询设备历史数据
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public DataHistory queryDeviceDataHistory(Map<String, String> param) throws Exception {
		String url = NBConstant.QUERY_DEVICE_HISTORY_DATA;
		Map<String, String> header = getHeaderMap(appId, secret);
		return cloudDataCollectorService.queryDeviceDataHistory(url, param, header);
	}

}
