package com.cn.entity;

/**
 * 拍卖商品list返回的bean
 * 
 * @author shining
 *
 */
public class AuctionResBean extends ReturnBean{
	/**
	 * 拍卖id
	 */
	private long auctionId;
	/**
	 * 拍卖期数
	 */
	private long term;
	/**
	 * 当前价格
	 */
	private double currentPrice;
	/**
	 * 一口价
	 */
	private long aPrice;
	/**
	 * 出价次数
	 */
	private int bidNumber;
	/**
	 * 拍卖状态 50010:(未开始) 50011:(正在进行) 50012:(结束)
	 */
	private int state;
	/**
	 * 开始时间
	 */
	private long startTime;
	/**
	 * 结束时间
	 */
	private long endTime;
	/**
	 * 拍卖产品图片
	 */
	private String imgUrl;
	/**
	 * 拍卖产品id
	 */
	private long productId;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 当前时间
	 */
	private long currentTime;
	


	public long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

	public long getTerm() {
		return term;
	}

	public void setTerm(long term) {
		this.term = term;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public long getaPrice() {
		return aPrice;
	}

	public void setaPrice(long aPrice) {
		this.aPrice = aPrice;
	}

	public int getBidNumber() {
		return bidNumber;
	}

	public void setBidNumber(int bidNumber) {
		this.bidNumber = bidNumber;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public Long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Long getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(long auctionId) {
		this.auctionId = auctionId;
	}

	@Override
	public String toString() {
		return "CollectAuctionResBean [auctionId=" + auctionId + ", term="
				+ term + ", currentPrice=" + currentPrice + ", aPrice="
				+ aPrice + ", bidNumber=" + bidNumber + ", state=" + state
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", imgUrl=" + imgUrl + ", productId=" + productId
				+ ", productName=" + productName + "]";
	}

}
