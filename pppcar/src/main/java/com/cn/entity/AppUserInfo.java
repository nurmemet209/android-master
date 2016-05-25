package com.cn.entity;

public class AppUserInfo {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 用户类型1:表示商户,2:表示普通用户
	 */
	private int userType=1;
	/**
	 * token
	 */
	private String appToken;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getAppToken() {
		return appToken;
	}
	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}
	@Override
	public String toString() {
		return "AppUserInfo [userId=" + userId + ", userType=" + userType
				+ ", appToken=" + appToken + "]";
	}
	
	
	
}
