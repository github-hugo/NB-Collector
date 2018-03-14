package com.nhb.NBIot.enums;

import com.nhb.utils.nhb_utils.common.StringUtil;

/**
 * 
 * @ClassName: NotifyTypeEnum
 * @Description: 订阅消息类型
 * @author XS guo
 * @date 2018年1月1日 下午3:09:17
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum NotifyTypeEnum {

	deviceAdded("deviceAdded", "添加新设备"), 
	deviceInfoChanged("deviceInfoChanged", "设备信息变化"), 
	deviceDataChanged("deviceDataChanged", "设备数据变化"), 
	deviceDeleted("deviceDeleted", "删除设备"), 
	deviceEvent("deviceEvent", "设备事件"),
	messageConfirm("messageConfirm", "消息确认"), 
	commandRsp("commandRsp", "响应命令"), 
	serviceInfoChanged("serviceInfoChanged", "设备信息"), 
	ruleEvent("ruleEvent", "规则事件"), 
	bindDevice("bindDevice", "设备绑定激活"), 
	deviceDatasChanged("deviceDatasChanged", "设备数据批量变化");

	private NotifyTypeEnum(String key, String value) {
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
			for (NotifyTypeEnum e : NotifyTypeEnum.values()) {
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
			for (NotifyTypeEnum e : NotifyTypeEnum.values()) {
				if (e.getValue().equals(value)) {
					return e.getKey();
				}
			}
		}
		return null;
	}
	
	
}
