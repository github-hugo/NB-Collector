/*
 * Copyright Notice:
 *      Copyright  1998-2008, Huawei Technologies Co., Ltd.  ALL Rights Reserved.
 *
 *      Warning: This computer software sourcecode is protected by copyright law
 *      and international treaties. Unauthorized reproduction or distribution
 *      of this sourcecode, or any portion of it, may result in severe civil and
 *      criminal penalties, and will be prosecuted to the maximum extent
 *      possible under the law.
 */
package com.nhb.NBIot.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhb.utils.nhb_utils.common.StringUtil;

public class StreamUtil {

	private static Logger logger = LoggerFactory.getLogger(StreamUtil.class);

	private static final String DEFAULT_ENCODING = "utf-8";

	public static String inputStream2String(InputStream in, String charsetName) {
		if (in == null) {
			return null;
		}

		InputStreamReader inReader = null;

		try {
			if (StringUtil.isNullOrEmpty(charsetName)) {
				inReader = new InputStreamReader(in, DEFAULT_ENCODING);
			} else {
				inReader = new InputStreamReader(in, charsetName);
			}

			int readLen = 0;
			char[] buffer = new char[1024];
			StringBuffer strBuf = new StringBuffer();
			while ((readLen = inReader.read(buffer)) != -1) {
				strBuf.append(buffer, 0, readLen);
			}

			return strBuf.toString();
		} catch (IOException e) {
			logger.info("Stream : " + e.getMessage());
		} finally {
			closeStream(inReader);
		}

		return null;
	}

	public static void closeStream(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				logger.error("Close stream : " + e.getMessage());
			}
		}
	}
}
