package com.cn.entity;

import java.util.Date;

public class AuctionListResBean {
	/**
	 * 拍卖id
	 */
	private Long auctionId;
	/**
	 * 竞拍状态
	 */
	private String auctionState;
	/**
	 * 成交时间
	 */
	private Date closingDate;
	/**
	 *开始时间 
	 */
	private Date startDate;
	/**
	 * 结束时间
	 */
	private Date endDate;
	
	/**
	 * 当前价格
	 */
	private Double currentPrice;
	/**
	 * 成交价格
	 */
	private Double closeingPrice;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 产品id
	 */
	private String productId;
	/**
	 * 是否支付
	 */
	private boolean pay;
	/**
	 * 产品图片
	 */
	private String imgUrl;
	

	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}
	public String getAuctionState() {
		return auctionState;
	}
	public void setAuctionState(String auctionState) {
		this.auctionState = auctionState;
	}
	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getClosingDate() {
		return closingDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public Double getCloseingPrice() {
		return closeingPrice;
	}
	public void setCloseingPrice(Double closeingPrice) {
		this.closeingPrice = closeingPrice;
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
	public boolean isPay() {
		return pay;
	}
	public void setPay(boolean pay) {
		this.pay = pay;
	}

	

}
