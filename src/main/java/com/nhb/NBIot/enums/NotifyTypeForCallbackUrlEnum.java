package com.nhb.NBIot.enums;

import com.nhb.NBIot.utils.NBConstant;
import com.nhb.utils.nhb_utils.common.StringUtil;

public enum NotifyTypeForCallbackUrlEnum {

	deviceAdded("deviceAdded", NBConstant.DEVICE_ADDED_CALLBACK_URL), 
	deviceInfoChanged("deviceInfoChanged", NBConstant.DEVICE_INFO_CHANGED_CALLBACK_URL), 
	deviceDataChanged("deviceDataChanged", NBConstant.DEVICE_DATA_CHANGED_CALLBACK_URL), 
	deviceDeleted("deviceDeleted", NBConstant.DEVICE_DELETED_CALLBACK_URL), 
	deviceEvent("deviceEvent", NBConstant.DEVICE_EVENT_CALLBACK_URL),
	messageConfirm("messageConfirm", NBConstant.MESSAGE_CONFIRM_CALLBACK_URL), 
	commandRsp("commandRsp", NBConstant.COMMAND_RSP_CALLBACK_URL), 
	serviceInfoChanged("serviceInfoChanged", NBConstant.SERVICE_INFO_CHANGED_CALLBACK_URL), 
	ruleEvent("ruleEvent", NBConstant.RULE_EVENT_CALLBACK_URL), 
	bindDevice("bindDevice", ""), 
	deviceDatasChanged("deviceDatasChanged", NBConstant.DEVICE_DATAS_CHANGED_CALLBACK_URL);

	private NotifyTypeForCallbackUrlEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	private String key;

	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * @Title: getValueByKey
	 * @Description: (根据key获取value)
	 * @return String
	 * @author XS guo
	 * @date 2018年1月1日 下午3:09:17
	 */
	public static String getValueByKey(String key) {
		if (!StringUtil.isNullOrEmpty(key)) {
			for (NotifyTypeForCallbackUrlEnum e : NotifyTypeForCallbackUrlEnum.values()) {
				if (e.getKey().equals(key)) {
					return e.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * @Title: getKeyByValue
	 * @Description: (根据value获取key)
	 * @return String author XS guo
	 * @date 2018年1月1日 下午3:09:17
	 */
	public static String getKeyByValue(String value) {
		if (!StringUtil.isNullOrEmpty(value)) {
			for (NotifyTypeForCallbackUrlEnum e : NotifyTypeForCallbackUrlEnum.values()) {
				if (e.getValue().equals(value)) {
					return e.getKey();
				}
			}
		}
		return null;
	}
	
	

}
