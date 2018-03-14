package com.nhb.NBIot.utils;

import org.apache.commons.lang3.StringUtils;

import com.nhb.utils.nhb_utils.file.PropertiesHelper;

/**
 * @ClassName: PropertyUtils
 * @Description:
 * @author XS guo
 * @date 2017年1月23日 上午11:18:38
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PropertyUtils {
	// 参数配置文件
	public static final String PROPERTIES_FILE_NAME = "nbiot.properties";

	/**
	 * @Title: getPropertyValue @Description: 返回配置文件参数常量 @return String @throws
	 */
	public static String getPropertyValue(String propertyName) {
		if (StringUtils.isBlank(propertyName)) {
			return null;
		}
		return PropertiesHelper.getInstance(PROPERTIES_FILE_NAME).getProperty(PROPERTIES_FILE_NAME, propertyName);
	}

}