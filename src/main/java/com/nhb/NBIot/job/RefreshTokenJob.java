package com.nhb.NBIot.job;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nhb.NBIot.dataaccess.service.TokenInfoService;
import com.nhb.NBIot.entity.TokenInfo;
import com.nhb.NBIot.utils.HttpsUtil;
import com.nhb.NBIot.utils.JsonUtil;
import com.nhb.NBIot.utils.NBConstant;
import com.nhb.NBIot.utils.StreamClosedHttpResponse;
import com.nhb.utils.nhb_utils.common.DateUtil;

@Component
public class RefreshTokenJob {

	@Autowired
	private TokenInfoService tokenInfoService;

	Date nowTime;

	/**
	 * 定时器每分钟执行一次，判断是否刷新token
	 */
	@SuppressWarnings({ "resource" })
	@Scheduled(cron = "0 */1 * * * ?")
	public void refreshToken() throws Exception {

		TokenInfo info = tokenInfoService.findByAppIdAndSecret(NBConstant.APPID, NBConstant.SECRET);
		Date lastActiveTime = info.getActiveTime();
		Integer expiresIn = info.getExpiresIn();
		nowTime = new Date();
		if ((lastActiveTime.getTime() + expiresIn * 1000) - nowTime.getTime() > 2 * 1000) {
			return;
		}
		// Two-Way Authentication
		HttpsUtil httpsUtil = new HttpsUtil();

		// get refreshToken
		String refreshToken = info.getRefreshToken();
		String appId = NBConstant.APPID;
		String secret = NBConstant.SECRET;
		String urlRefreshToken = NBConstant.REFRESH_TOKEN;

		Map<String, Object> param_reg = new HashMap<>();
		param_reg.put("appId", appId);
		param_reg.put("secret", secret);
		param_reg.put("refreshToken", refreshToken);

		String jsonRequest = JsonUtil.jsonObj2Sting(param_reg);
		StreamClosedHttpResponse bodyRefreshToken = httpsUtil.doPostJsonGetStatusLine(urlRefreshToken, jsonRequest);

		// resolve the value of accessToken from responseLogin.
		TokenInfo entity = JsonUtil.jsonString2SimpleObj(bodyRefreshToken.getContent(), TokenInfo.class);

		info.setAccessToken(entity.getAccessToken());
		info.setExpiresIn(entity.getExpiresIn());
		info.setRefreshToken(entity.getRefreshToken());
		info.setScope(entity.getScope());
		info.setTokenType(entity.getTokenType());
		info.setActiveTime(new Date());
		tokenInfoService.save(info);
		System.out.println(DateUtil.format(nowTime) + "  token刷新成功！");
	}

}
