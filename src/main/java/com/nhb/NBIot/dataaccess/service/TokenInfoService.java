package com.nhb.NBIot.dataaccess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhb.NBIot.dataaccess.dao.TokenInfoDao;
import com.nhb.NBIot.entity.TokenInfo;

@Service
public class TokenInfoService {

	@Autowired
	private TokenInfoDao tokenInfoDao;

	public TokenInfo save(TokenInfo info) {
		return tokenInfoDao.save(info);
	}

	public TokenInfo findByAppIdAndSecret(String appId, String secret) {
		return tokenInfoDao.findByAppIdAndSecret(appId, secret);
	}
}
