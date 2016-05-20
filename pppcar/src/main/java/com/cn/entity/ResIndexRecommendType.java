package com.cn.entity;


public class ResIndexRecommendType {
	
	private boolean isLarge;//是否大图
	
	private String imgUrl;//图片url
	
	private String imgLinkUrl;//图片点击跳转地址

	public boolean getIsLarge() {
		return isLarge;
	}

	public void setIsLarge(boolean isLarge) {
		this.isLarge = isLarge;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgLinkUrl() {
		return imgLinkUrl;
	}

	public void setImgLinkUrl(String imgLinkUrl) {
		this.imgLinkUrl = imgLinkUrl;
	}
	
}
