package com.nhb.NBIot.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.nhb.NBIot.dataaccess.service.DeviceInfoService;
import com.nhb.NBIot.entity.DeviceInfo;
import com.nhb.NBIot.request.device.DeleteDeviceRequest;
import com.nhb.NBIot.request.device.RegisterDeviceRequest;
import com.nhb.NBIot.request.device.UpdateDeviceInfoRequest;
import com.nhb.NBIot.service.DeviceManageService;
import com.nhb.NBIot.utils.NBConstant;
import com.nhb.utils.nhb_utils.common.RestResultDto;
import com.nhb.utils.nhb_utils.common.StringUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("nhb/nbiot/device/manage/v1")
public class DeviceManageController {

	@Autowired
	private DeviceManageService deviceManageService;

	@Autowired
	private DeviceInfoService deviceInfoService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "注册直连设备")
	@RequestMapping(value = "registerDirectlyConnectedDevice", method = { RequestMethod.POST })
	public RestResultDto registerDirectlyConnectedDevice(@RequestBody RegisterDeviceRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			if (StringUtil.isNullOrEmpty(request.getNodeId())) {
				throw new Exception("nodeId不能为空！");
			}

			Map<String, String> paramReg = new HashMap<>();
			paramReg.put("verifyCode", request.getVerifyCode().toUpperCase());
			paramReg.put("nodeId", request.getNodeId().toUpperCase());
			paramReg.put("timeout", String.valueOf(request.getTimeout() == null ? 0 : request.getTimeout()));
			if (!StringUtil.isNullOrEmpty(request.getEndUserId())) {
				paramReg.put("endUserId", request.getEndUserId());
			}
			// psk字段去电信平台注册的时候，会注册失败，暂时不使用
//			if (!StringUtil.isNullOrEmpty(request.getPsk())) {
//				paramReg.put("psk", request.getPsk());
//			}

			DeviceInfo info = deviceManageService.registerDirectlyConnectedDevice(paramReg);
			info.setNodeId(request.getNodeId());
			info.setEndUserId(request.getEndUserId());
			info.setLongitude(request.getLongitude());
			info.setLatitude(request.getLatitude());
			info.setGpsLng(request.getGpsLng());
			info.setGpsLat(request.getGpsLat());
			data = deviceInfoService.save(info);
			data = info;
			msg = "注册成功！";

		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "注册失败！";
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "修改设备信息")
	@RequestMapping(value = "modifyDeviceInfo", method = { RequestMethod.POST })
	public RestResultDto modifyDeviceInfo(@RequestBody UpdateDeviceInfoRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = "修改失败！";
		Object data = null;
		String exception = null;
		try {
			if (StringUtil.isNullOrEmpty(request.getDeviceId())) {
				throw new Exception("deviceId不能为空!");
			}
			Map<String, Object> paramReg = new HashMap<>();
			paramReg.put("appId", NBConstant.APPID);
			paramReg.put("deviceId", request.getDeviceId());
			paramReg.put("name", request.getRequest().getName());
			paramReg.put("manufacturerId", request.getRequest().getManufacturerId());
			paramReg.put("manufacturerName", request.getRequest().getManufacturerName());
			paramReg.put("location", request.getRequest().getLocation());
			paramReg.put("deviceType", request.getRequest().getDeviceType());
			paramReg.put("model", request.getRequest().getModel());
			paramReg.put("protocolType", request.getRequest().getProtocolType());
			paramReg.put("region", request.getRequest().getRegion());
			paramReg.put("organization", request.getRequest().getOrganization());
			paramReg.put("endUser", request.getRequest().getEndUser());
			paramReg.put("mute", request.getRequest().getMute());
			// paramReg.put("deviceConfig",
			// request.getRequest().getDeviceConfig());
			// paramReg.put("timezone", request.getRequest().getTimezone());

			boolean flag = deviceManageService.modifyDeviceInfo(paramReg);
			data = flag;
			if (flag) {
				msg = "修改成功！";
			}
		} catch (Exception e) {
			data = false;
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "修改失败！";
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "删除直连设备")
	@RequestMapping(value = "deleteDirectlyConnectedDevice", method = { RequestMethod.POST })
	public RestResultDto deleteDirectlyConnectedDevice(@RequestBody DeleteDeviceRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = "删除失败！";
		Object data = null;
		String exception = null;
		try {
			if (StringUtil.isNullOrEmpty(request.getDeviceId())) {
				throw new Exception("deviceId不能为空!");
			}
			Map<String, Object> paramReg = Maps.newHashMap();
			paramReg.put("deviceId", request.getDeviceId());
			paramReg.put("cascade", request.isCascade());

			boolean flag = deviceManageService.deleteDirectlyConnectedDevice(paramReg);
			data = flag;
			if (flag) {
				msg = "删除成功！";
			}
		} catch (Exception e) {
			data = false;
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}

}
