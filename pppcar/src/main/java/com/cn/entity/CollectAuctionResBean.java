package com.cn.entity;

import java.util.List;

public class CollectAuctionResBean {
	/**
	 * 返回查询的时间
	 */
	private List<String> time;
	/**
	 * 返回所有的拍卖
	 */
	private List<AuctionResBean> auctionResBeans;

	public List<String> getTime() {
		return time;
	}

	public void setTime(List<String> time) {
		this.time = time;
	}

	public List<AuctionResBean> getAuctionResBeans() {
		return auctionResBeans;
	}

	public void setAuctionResBeans(List<AuctionResBean> auctionResBeans) {
		this.auctionResBeans = auctionResBeans;
	}

	@Override
	public String toString() {
		return "CollectAuctionResBean [time=" + time + ", auctionResBeans="
				+ auctionResBeans + "]";
	}

}
