package com.nhb.NBIot.api.callback;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nhb.NBIot.dataaccess.service.DeviceCircuitService;
import com.nhb.NBIot.dataaccess.service.DeviceInfoService;
import com.nhb.NBIot.dataaccess.service.HistoryDataService;
import com.nhb.NBIot.dataaccess.service.RealtimeDataService;
import com.nhb.NBIot.dto.data.DeviceDataChangedDTO;
import com.nhb.NBIot.dto.data.ThreePhaseFourCircuitsDTO;
import com.nhb.NBIot.dto.device.DeviceAddedDTO;
import com.nhb.NBIot.entity.DeviceCircuit;
import com.nhb.NBIot.entity.DeviceInfo;
import com.nhb.NBIot.entity.HistoryData;
import com.nhb.NBIot.entity.RealtimeData;
import com.nhb.NBIot.utils.DateTransUtil;
import com.nhb.utils.nhb_utils.common.DateUtil;
import com.nhb.utils.nhb_utils.common.StringUtil;
import com.nhb.utils.nhb_utils.json.JsonMapper;

/**
 * 
 * @ClassName: MessageNotificationController
 * @Description: 消息推送接口
 * @author XS guo
 * @date 2018年1月3日 下午3:09:17
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping("/na/iocm/devNotify/v1.1.0")
public class MessageNotificationController {

	@Autowired
	private DeviceInfoService deviceInfoService;

	@Autowired
	private HistoryDataService historyDataService;

	@Autowired
	private RealtimeDataService realtimeDataService;

	@Autowired
	private DeviceCircuitService deviceCircuitService;

	/**
	 * 注册设备的通知
	 */
	@RequestMapping(value = "addDevice", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> addDevice(@RequestBody DeviceAddedDTO dto) throws Exception {
		// 设备注册后数据处理
		DeviceInfo deviceInfo = deviceInfoService.findById(dto.getDeviceId());
		if (null == deviceInfo) {
			deviceInfo = new DeviceInfo();
			deviceInfo.setDeviceId(dto.getDeviceId());
			deviceInfoService.save(deviceInfo);
		}
		// 反射类里的字段属性以及属性值
		for (Field dtoField : dto.getDeviceInfo().getClass().getDeclaredFields()) {
			dtoField.setAccessible(true);
			if (!StringUtil.isNullOrEmpty(String.valueOf(dtoField.get(dto.getDeviceInfo())))) {
				for (Field dataField : deviceInfo.getClass().getDeclaredFields()) {
					dataField.setAccessible(true);
					if (dataField.getName().equals(dtoField.getName())) {
						dataField.set(deviceInfo, dtoField.get(dto.getDeviceInfo()));
					}
				}
			}
		}
		deviceInfoService.save(deviceInfo);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 设备信息修改的通知
	 */
	@RequestMapping(value = "updateDeviceInfo", method = {
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> updateDeviceInfo(@RequestBody DeviceAddedDTO dto) throws Exception {
		// 设备注册后数据处理
		DeviceInfo deviceInfo = deviceInfoService.findById(dto.getDeviceId());
		if (null == deviceInfo) {
			deviceInfo = new DeviceInfo();
			deviceInfo.setDeviceId(dto.getDeviceId());
			deviceInfoService.save(deviceInfo);
		}
		// 反射类里的字段属性以及属性值
		for (Field dtoField : dto.getDeviceInfo().getClass().getDeclaredFields()) {
			dtoField.setAccessible(true);
			if (!StringUtil.isNullOrEmpty(String.valueOf(dtoField.get(dto.getDeviceInfo())))) {
				for (Field dataField : deviceInfo.getClass().getDeclaredFields()) {
					dataField.setAccessible(true);
					if (dataField.getName().equals(dtoField.getName())) {
						dataField.set(deviceInfo, dtoField.get(dto.getDeviceInfo()));
					}
				}
			}
		}
		deviceInfoService.save(deviceInfo);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 设备数据变化的通知
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "updateDeviceData", method = {
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> updateDeviceData(@RequestBody DeviceDataChangedDTO dto) throws Exception {

		// 更新实时数据表数据
		List<DeviceCircuit> deviceCircuits = deviceCircuitService.findByDeviceId(dto.getDeviceId());
		Object[] array = new Object[4];
		String serviceId = dto.getService().getServiceId();
		JsonMapper jsonMapper = new JsonMapper();
		Map<String, Object> mapValue = jsonMapper.convertValue(dto.getService().getData(), Map.class);
		String meterTime = String.valueOf(mapValue.get("meterTime"));
		// 三相单路表
		if (serviceId.equals("ThreePhaseSingleCircuit")) {
			array[0] = dto.getService().getData();
		}
		// 三相四路表
		if (serviceId.equals("ThreePhaseFourCircuits")) {
			ThreePhaseFourCircuitsDTO dtoData = null;
			dtoData = new ThreePhaseFourCircuitsDTO();
			dtoData.setMeterTime(String.valueOf(mapValue.get("meterTime")));
			dtoData.setFrequency(Double.parseDouble(String.valueOf(mapValue.get("frequency"))));
			dtoData.setaVolt(Double.parseDouble(String.valueOf(mapValue.get("aVolt"))));
			dtoData.setbVolt(Double.parseDouble(String.valueOf(mapValue.get("bVolt"))));
			dtoData.setcVolt(Double.parseDouble(String.valueOf(mapValue.get("cVolt"))));
			dtoData.setaCurrent(Double.parseDouble(String.valueOf(mapValue.get("aCurrent1"))));
			dtoData.setbCurrent(Double.parseDouble(String.valueOf(mapValue.get("bCurrent1"))));
			dtoData.setcCurrent(Double.parseDouble(String.valueOf(mapValue.get("cCurrent1"))));
			dtoData.setKw(Double.parseDouble(String.valueOf(mapValue.get("kw1"))));
			dtoData.setaKw(Double.parseDouble(String.valueOf(mapValue.get("aKw1"))));
			dtoData.setbKw(Double.parseDouble(String.valueOf(mapValue.get("bKw1"))));
			dtoData.setcKw(Double.parseDouble(String.valueOf(mapValue.get("cKw1"))));
			dtoData.setKvar(Double.parseDouble(String.valueOf(mapValue.get("kvar1"))));
			dtoData.setKva(Double.parseDouble(String.valueOf(mapValue.get("kva1"))));
			dtoData.setPowerFactor(Double.parseDouble(String.valueOf(mapValue.get("powerFactor1"))));
			dtoData.setKwhTotal(Double.parseDouble(String.valueOf(mapValue.get("kwhTotal1"))));
			dtoData.setaKwh(Double.parseDouble(String.valueOf(mapValue.get("aKwh1"))));
			dtoData.setbKwh(Double.parseDouble(String.valueOf(mapValue.get("bKwh1"))));
			dtoData.setcKwh(Double.parseDouble(String.valueOf(mapValue.get("cKwh1"))));
			dtoData.setKvarh(Double.parseDouble(String.valueOf(mapValue.get("kvarh1"))));
			array[0] = dtoData;

			dtoData = new ThreePhaseFourCircuitsDTO();
			dtoData.setMeterTime(String.valueOf(mapValue.get("meterTime")));
			dtoData.setFrequency(Double.parseDouble(String.valueOf(mapValue.get("frequency"))));
			dtoData.setaVolt(Double.parseDouble(String.valueOf(mapValue.get("aVolt"))));
			dtoData.setbVolt(Double.parseDouble(String.valueOf(mapValue.get("bVolt"))));
			dtoData.setcVolt(Double.parseDouble(String.valueOf(mapValue.get("cVolt"))));
			dtoData.setaCurrent(Double.parseDouble(String.valueOf(mapValue.get("aCurrent2"))));
			dtoData.setbCurrent(Double.parseDouble(String.valueOf(mapValue.get("bCurrent2"))));
			dtoData.setcCurrent(Double.parseDouble(String.valueOf(mapValue.get("cCurrent2"))));
			dtoData.setKw(Double.parseDouble(String.valueOf(mapValue.get("kw2"))));
			dtoData.setaKw(Double.parseDouble(String.valueOf(mapValue.get("aKw2"))));
			dtoData.setbKw(Double.parseDouble(String.valueOf(mapValue.get("bKw2"))));
			dtoData.setcKw(Double.parseDouble(String.valueOf(mapValue.get("cKw2"))));
			dtoData.setKvar(Double.parseDouble(String.valueOf(mapValue.get("kvar2"))));
			dtoData.setKva(Double.parseDouble(String.valueOf(mapValue.get("kva2"))));
			dtoData.setPowerFactor(Double.parseDouble(String.valueOf(mapValue.get("powerFactor2"))));
			dtoData.setKwhTotal(Double.parseDouble(String.valueOf(mapValue.get("kwhTotal2"))));
			dtoData.setaKwh(Double.parseDouble(String.valueOf(mapValue.get("aKwh2"))));
			dtoData.setbKwh(Double.parseDouble(String.valueOf(mapValue.get("bKwh2"))));
			dtoData.setcKwh(Double.parseDouble(String.valueOf(mapValue.get("cKwh2"))));
			dtoData.setKvarh(Double.parseDouble(String.valueOf(mapValue.get("kvarh2"))));
			array[1] = dtoData;

			dtoData = new ThreePhaseFourCircuitsDTO();
			dtoData.setMeterTime(String.valueOf(mapValue.get("meterTime")));
			dtoData.setFrequency(Double.parseDouble(String.valueOf(mapValue.get("frequency"))));
			dtoData.setaVolt(Double.parseDouble(String.valueOf(mapValue.get("aVolt"))));
			dtoData.setbVolt(Double.parseDouble(String.valueOf(mapValue.get("bVolt"))));
			dtoData.setcVolt(Double.parseDouble(String.valueOf(mapValue.get("cVolt"))));
			dtoData.setaCurrent(Double.parseDouble(String.valueOf(mapValue.get("aCurrent3"))));
			dtoData.setbCurrent(Double.parseDouble(String.valueOf(mapValue.get("bCurrent3"))));
			dtoData.setcCurrent(Double.parseDouble(String.valueOf(mapValue.get("cCurrent3"))));
			dtoData.setKw(Double.parseDouble(String.valueOf(mapValue.get("kw3"))));
			dtoData.setaKw(Double.parseDouble(String.valueOf(mapValue.get("aKw3"))));
			dtoData.setbKw(Double.parseDouble(String.valueOf(mapValue.get("bKw3"))));
			dtoData.setcKw(Double.parseDouble(String.valueOf(mapValue.get("cKw3"))));
			dtoData.setKvar(Double.parseDouble(String.valueOf(mapValue.get("kvar3"))));
			dtoData.setKva(Double.parseDouble(String.valueOf(mapValue.get("kva3"))));
			dtoData.setPowerFactor(Double.parseDouble(String.valueOf(mapValue.get("powerFactor3"))));
			dtoData.setKwhTotal(Double.parseDouble(String.valueOf(mapValue.get("kwhTotal3"))));
			dtoData.setaKwh(Double.parseDouble(String.valueOf(mapValue.get("aKwh3"))));
			dtoData.setbKwh(Double.parseDouble(String.valueOf(mapValue.get("bKwh3"))));
			dtoData.setcKwh(Double.parseDouble(String.valueOf(mapValue.get("cKwh3"))));
			dtoData.setKvarh(Double.parseDouble(String.valueOf(mapValue.get("kvarh3"))));
			array[2] = dtoData;

			dtoData = new ThreePhaseFourCircuitsDTO();
			dtoData.setMeterTime(String.valueOf(mapValue.get("meterTime")));
			dtoData.setFrequency(Double.parseDouble(String.valueOf(mapValue.get("frequency"))));
			dtoData.setaVolt(Double.parseDouble(String.valueOf(mapValue.get("aVolt"))));
			dtoData.setbVolt(Double.parseDouble(String.valueOf(mapValue.get("bVolt"))));
			dtoData.setcVolt(Double.parseDouble(String.valueOf(mapValue.get("cVolt"))));
			dtoData.setaCurrent(Double.parseDouble(String.valueOf(mapValue.get("aCurrent4"))));
			dtoData.setbCurrent(Double.parseDouble(String.valueOf(mapValue.get("bCurrent4"))));
			dtoData.setcCurrent(Double.parseDouble(String.valueOf(mapValue.get("cCurrent4"))));
			dtoData.setKw(Double.parseDouble(String.valueOf(mapValue.get("kw4"))));
			dtoData.setaKw(Double.parseDouble(String.valueOf(mapValue.get("aKw4"))));
			dtoData.setbKw(Double.parseDouble(String.valueOf(mapValue.get("bKw4"))));
			dtoData.setcKw(Double.parseDouble(String.valueOf(mapValue.get("cKw4"))));
			dtoData.setKvar(Double.parseDouble(String.valueOf(mapValue.get("kvar4"))));
			dtoData.setKva(Double.parseDouble(String.valueOf(mapValue.get("kva4"))));
			dtoData.setPowerFactor(Double.parseDouble(String.valueOf(mapValue.get("powerFactor4"))));
			dtoData.setKwhTotal(Double.parseDouble(String.valueOf(mapValue.get("kwhTotal4"))));
			dtoData.setaKwh(Double.parseDouble(String.valueOf(mapValue.get("aKwh4"))));
			dtoData.setbKwh(Double.parseDouble(String.valueOf(mapValue.get("bKwh4"))));
			dtoData.setcKwh(Double.parseDouble(String.valueOf(mapValue.get("cKwh4"))));
			dtoData.setKvarh(Double.parseDouble(String.valueOf(mapValue.get("kvarh4"))));
			array[3] = dtoData;
		}

		RealtimeData realtimeData = null;
		if (!CollectionUtils.isEmpty(deviceCircuits)) {
			for (DeviceCircuit circuit : deviceCircuits) {
				realtimeData = realtimeDataService.findByDeviceId(circuit.getCircuitId());
				if (null == realtimeData) {
					realtimeData = new RealtimeData();
					realtimeData.setDeviceId(circuit.getCircuitId());
				}
				
				realtimeData.setReadTime(DateTransUtil.UTC2Date(dto.getService().getEventTime()));
				realtimeData.setMeterTime(DateUtil.parse(meterTime, DateUtil.DATETIME_FORMAT));
				realtimeData.setData(array[Integer.parseInt(circuit.getCircuitNo()) - 1]);
				realtimeData.setServiceId(dto.getService().getServiceId());
				realtimeDataService.save(realtimeData);

				HistoryData historyData = new HistoryData();
				historyData.setId(dto.getRequestId());
				historyData.setDeviceId(circuit.getCircuitId());
				historyData.setServiceId(dto.getService().getServiceId());
				historyData.setMeterTime(DateUtil.parse(meterTime, DateUtil.DATETIME_FORMAT));
				historyData.setReadTime(DateTransUtil.UTC2Date(dto.getService().getEventTime()));
				historyData.setData(array[Integer.parseInt(circuit.getCircuitNo()) - 1]);
				historyDataService.save(historyData);
			}
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 设备删除的通知
	 */
	@RequestMapping(value = "deletedDevice", method = {
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> deleteDevice(@RequestBody DeviceAddedDTO dto) {
		DeviceInfo deviceInfo = deviceInfoService.findById(dto.getDeviceId());
		if (null != deviceInfo) {
			deviceInfoService.delete(deviceInfo);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
