package com.nhb.NBIot.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "token_info")
public class TokenInfo {

	@Id
	@Field("app_id")
	private String appId;

	@Field("secret")
	private String secret;

	@Field("scope")
	private String scope;

	@Field("token_type")
	private String tokenType;

	@Field("expires_in")
	private Integer expiresIn;

	@Field("access_token")
	private String accessToken;

	@Field("refresh_token")
	private String refreshToken;

	@Field("active_time")
	private Date activeTime;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

}
