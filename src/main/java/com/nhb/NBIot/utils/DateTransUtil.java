package com.nhb.NBIot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.nhb.utils.nhb_utils.common.DateUtil;

/**
 * 
 * @ClassName: DateTransUtil
 * @Description: UTC格式转换类
 * @author XS guo
 * @date 2018年1月10日 下午3:09:17
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DateTransUtil {

	/**
	 * UTC时间转换成String格式
	 * 
	 * @param utcTime
	 * @return
	 * @throws Exception
	 */
	public static String UTC2String(String utcTime) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		return DateUtil.format(df.parse(utcTime));
	}

	/**
	 * UTC时间转换成Date格式
	 * 
	 * @param utcTime
	 * @return
	 * @throws Exception
	 */
	public static Date UTC2Date(String utcTime) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		return df.parse(utcTime);
	}

}
