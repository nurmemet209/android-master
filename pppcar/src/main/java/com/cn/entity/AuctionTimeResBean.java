package com.cn.entity;

import java.util.List;

public class AuctionTimeResBean {
	private String date;
	private List<AuctionBidResBean> auctionBidResBean;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<AuctionBidResBean> getAuctionBidResBean() {
		return auctionBidResBean;
	}

	public void setAuctionBidResBean(List<AuctionBidResBean> auctionBidResBean) {
		this.auctionBidResBean = auctionBidResBean;
	}
	
	
}
