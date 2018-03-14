package com.nhb.NBIot.api;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.nhb.NBIot.enums.NotifyTypeForCallbackUrlEnum;
import com.nhb.NBIot.request.data.DataHistoryRequest;
import com.nhb.NBIot.request.data.QueryDevicesRequest;
import com.nhb.NBIot.service.DataCollectionService;
import com.nhb.NBIot.utils.NBConstant;
import com.nhb.utils.nhb_utils.common.RestResultDto;
import com.nhb.utils.nhb_utils.common.StringUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("nhb/nbiot/data/collector/v1")
public class DataCollectorController {

	@Autowired
	private DataCollectionService dataCollectionService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "单个设备信息查询")
	@RequestMapping(value = "queryDevice", method = { RequestMethod.POST })
	public RestResultDto queryDevice(@RequestBody String deviceId) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			Map<String, String> param = Maps.newHashMap();
			param.put("deviceId", deviceId);
			param.put("appId", NBConstant.APPID);
			data = dataCollectionService.queryDevice(param);
			msg = "查询成功！";

		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "查询失败！";
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "按条件批量查询设备列表信息")
	@RequestMapping(value = "queryDevices", method = { RequestMethod.POST })
	public RestResultDto queryDevices(@RequestBody QueryDevicesRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			if (StringUtil.isNullOrEmpty(request.getGatewayId())
					&& StringUtil.isNullOrEmpty(String.valueOf(request.getPageNo()))) {
				throw new Exception("gatewayId和pageNo不能同时为空！");
			}
			// 分页减 一
			if (request.getPageNo() != null) {
				request.setPageNo(request.getPageNo() - 1);
			}
			Map<String, String> param = Maps.newHashMap();
			// 反射类里的字段属性以及属性值
			for (Field field : request.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (!StringUtil.isNullOrEmpty(String.valueOf(field.get(request)))) {
					param.put(field.getName(), String.valueOf(field.get(request)));
				}
			}
			data = dataCollectionService.queryDevices(param);
			msg = "查询成功！";

		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "查询失败！";
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "订阅平台数据")
	@RequestMapping(value = "subscribe", method = { RequestMethod.POST })
	public RestResultDto subscribe(@RequestBody String notifyType) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = "订阅失败！";
		Object data = null;
		String exception = null;
		try {

			Map<String, String> mapValue = Maps.newHashMap();
			mapValue.put("notifyType", notifyType);
			mapValue.put("callbackurl", NotifyTypeForCallbackUrlEnum.getValueByKey(notifyType));
			data = dataCollectionService.subscribeNotification(mapValue);
			if (Boolean.parseBoolean(String.valueOf(data))) {
				msg = "订阅成功！";
			}

		} catch (Exception e) {
			data = false;
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "订阅失败！";
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询设备历史数据")
	@RequestMapping(value = "deviceDataHistory", method = { RequestMethod.POST })
	public RestResultDto deviceDataHistory(@RequestBody DataHistoryRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			if (StringUtil.isNullOrEmpty(request.getDeviceId()) || StringUtil.isNullOrEmpty(request.getGatewayId())) {
				throw new Exception("deviceId或者gatewayId不能为空！");
			}
			// 分页减 一
			if (request.getPageNo() != null) {
				request.setPageNo(request.getPageNo() - 1);
			}
			Map<String, String> param = Maps.newHashMap();
			// 反射类里的字段属性以及属性值
			for (Field field : request.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (!StringUtil.isNullOrEmpty(String.valueOf(field.get(request)))) {
					param.put(field.getName(), String.valueOf(field.get(request)));
				}
			}
			data = dataCollectionService.queryDeviceDataHistory(param);
			msg = "查询历史数据成功！";

		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "查询历史数据失败！";
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}

}
