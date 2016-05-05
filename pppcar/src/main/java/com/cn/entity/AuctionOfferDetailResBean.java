package com.cn.entity;

import java.util.List;

public class AuctionOfferDetailResBean {
	/**
	 * 图片
	 */
	private String imgUrl;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 产品id
	 */
	private String productId;
	/**
	 * 当前价
	 */
	private Double currentPrice;
	/**
	 * 是否有一口价
	 */
	private boolean isaPrice;
	/**
	 * 加价幅度详细
	 */
	private List<Double> addRangePrice;
	/**
	 * 起拍价
	 */
	private Double initPrice;
	/**
	 * 一口价
	 */
	private Double aPrice;
	/**
	 * 最小加价幅度
	 */
	private Double rangPrice;
	/**
	 * 最低成交价
	 */
	private Double minimumPrice;
	/**
	 * 最大加价幅度
	 */
	private Double maxRangPrice;

	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public boolean isIsaPrice() {
		return isaPrice;
	}
	public void setIsaPrice(boolean isaPrice) {
		this.isaPrice = isaPrice;
	}
	public List<Double> getAddRangePrice() {
		return addRangePrice;
	}
	public void setAddRangePrice(List<Double> addRangePrice) {
		this.addRangePrice = addRangePrice;
	}
	public Double getInitPrice() {
		return initPrice;
	}
	public void setInitPrice(Double initPrice) {
		this.initPrice = initPrice;
	}
	public Double getaPrice() {
		return aPrice;
	}
	public void setaPrice(Double aPrice) {
		this.aPrice = aPrice;
	}
	public Double getRangPrice() {
		return rangPrice;
	}
	public void setRangPrice(Double rangPrice) {
		this.rangPrice = rangPrice;
	}
	public Double getMinimumPrice() {
		return minimumPrice;
	}
	public void setMinimumPrice(Double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
	public Double getMaxRangPrice() {
		return maxRangPrice;
	}
	public void setMaxRangPrice(Double maxRangPrice) {
		this.maxRangPrice = maxRangPrice;
	}

}
