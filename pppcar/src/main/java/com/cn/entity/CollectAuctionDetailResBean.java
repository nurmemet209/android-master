package com.cn.entity;

import java.util.List;

public class CollectAuctionDetailResBean extends AuctionResBean{
	/**
	 * 市场价
	 */
	private Double retailPrice;
	/**
	 * 成交人
	 */
	private String transactionPerson;
	/**
	 * 是否有出价记录
	 */
	private boolean bidRecord;
	/**
	 * 是否支付
	 * 
	 */
	private boolean pay;
	/**
	 * 竞拍是否成功，成功，失败
	 */
	private String auctionState;
	/**
	 * 是否有一口价
	 */
	private boolean isaPrice;
	
	
	public boolean isIsaPrice() {
		return isaPrice;
	}

	public void setIsaPrice(boolean isaPrice) {
		this.isaPrice = isaPrice;
	}

	private List<AuctionTimeResBean> auctionTimeResBeans;
	
	public String getAuctionState() {
		return auctionState;
	}

	public void setAuctionState(String auctionState) {
		this.auctionState = auctionState;
	}

	public boolean isPay() {
		return pay;
	}

	public void setPay(boolean pay) {
		this.pay = pay;
	}

	public boolean isBidRecord() {
		return bidRecord;
	}


	
	public void setBidRecord(boolean bidRecord) {
		this.bidRecord = bidRecord;
	}

	public String getTransactionPerson() {
		return transactionPerson;
	}

	public void setTransactionPerson(String transactionPerson) {
		this.transactionPerson = transactionPerson;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public List<AuctionTimeResBean> getAuctionTimeResBeans() {
		return auctionTimeResBeans;
	}

	public void setAuctionTimeResBeans(List<AuctionTimeResBean> auctionTimeResBeans) {
		this.auctionTimeResBeans = auctionTimeResBeans;
	}


}
