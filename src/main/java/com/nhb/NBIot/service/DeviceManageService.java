package com.nhb.NBIot.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.nhb.NBIot.cloud.service.CloudDeviceManageService;
import com.nhb.NBIot.dataaccess.service.TokenInfoService;
import com.nhb.NBIot.entity.DeviceInfo;
import com.nhb.NBIot.utils.NBConstant;

@Service
public class DeviceManageService {

	@Autowired
	private CloudDeviceManageService cloudDeviceManageService;

	@Autowired
	private TokenInfoService tokenInfoService;

	String appId = NBConstant.APPID;
	String secret = NBConstant.SECRET;

	public DeviceInfo registerDirectlyConnectedDevice(Map<String, String> param) throws Exception {
		String url = NBConstant.REGISTER_DEVICE;
		// 获取appId以及accessToken
		Map<String, String> header = getHeaderMap(appId, secret);
		return cloudDeviceManageService.registerDirectlyConnectedDevice(url, param, header);
	}

	public boolean modifyDeviceInfo(Map<String, Object> param) {
		String deviceId = String.valueOf(param.get("deviceId"));
		String url = NBConstant.MODIFY_DEVICE_INFO + "/" + deviceId;
		Map<String, String> header = getHeaderMap(appId, secret);
		return cloudDeviceManageService.modifyDeviceInfo(url, header, param);
	}

	public boolean deleteDirectlyConnectedDevice(Map<String, Object> param) {
		String deviceId = String.valueOf(param.get("deviceId"));
		String url = NBConstant.DELETE_DEVICE + "/" + deviceId;
		Map<String, String> header = getHeaderMap(appId, secret);
		return cloudDeviceManageService.deleteDirectlyConnectedDevice(url, header);
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
}
