package com.nhb.NBIot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.nhb.NBIot.dataaccess.service.TokenInfoService;
import com.nhb.NBIot.entity.TokenInfo;
import com.nhb.NBIot.utils.HttpsUtil;
import com.nhb.NBIot.utils.JsonUtil;
import com.nhb.NBIot.utils.NBConstant;
import com.nhb.NBIot.utils.StreamClosedHttpResponse;
import com.nhb.utils.nhb_utils.context.SpringContextHolder;

@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan("com.nhb.NBIot")
@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@EnableScheduling
@SpringBootApplication
public class NBIoTApplication {

	// 注入tokenService
	private static TokenInfoService getTokenInfoService() {
		return SpringContextHolder.getBean("tokenInfoService");
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		// 启动服务
		SpringApplication.run(NBIoTApplication.class, args);

		// 启动服务后刷新token
		// Two-Way Authentication
		HttpsUtil httpsUtil = new HttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();

		String appId = NBConstant.APPID;
		String secret = NBConstant.SECRET;
		String urlLogin = NBConstant.APP_AUTH;
		Map<String, String> param = new HashMap<>();
		param.put("appId", appId);
		param.put("secret", secret);

		StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, param);
		// resolve the value of accessToken from responseLogin.
		TokenInfo entity = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), TokenInfo.class);

		TokenInfo info = getTokenInfoService().findByAppIdAndSecret(appId, secret);
		if (null == info) {
			info = new TokenInfo();
			info.setAppId(appId);
			info.setSecret(secret);
		}
		info.setAccessToken(entity.getAccessToken());
		info.setExpiresIn(entity.getExpiresIn());
		info.setRefreshToken(entity.getRefreshToken());
		info.setScope(entity.getScope());
		info.setTokenType(entity.getTokenType());
		info.setActiveTime(new Date());
		getTokenInfoService().save(info);

	}

}
