package com.cn.entity;

import java.util.Date;

public class AuctionBidResBean {
	/**
	 * 价格
	 */
	private Double bidPrice;
	/**
	 * 出价时间
	 */
	private String bidDate;

	/**
	 * 是否领先,是否成交
	 */
	private boolean lead;
	/**
	 * 商户电话号码
	 */
	private String mobileNumber;
	public Double getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}
	public String getBidDate() {
		return bidDate;
	}
	public void setBidDate(String bidDate) {
		this.bidDate = bidDate;
	}
	public boolean isLead() {
		return lead;
	}
	public void setLead(boolean lead) {
		this.lead = lead;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	

}
