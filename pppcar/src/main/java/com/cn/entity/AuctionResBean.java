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
	private Long auctionId;
	/**
	 * 拍卖期数
	 */
	private Long term;
	/**
	 * 当前价格
	 */
	private Double currentPrice;
	/**
	 * 一口价
	 */
	private Double aPrice;
	/**
	 * 出价次数
	 */
	private Integer bidNumber;
	/**
	 * 拍卖状态 50010:(未开始) 50011:(正在进行) 50012:(结束)
	 */
	private Integer state;
	/**
	 * 开始时间
	 */
	private Long startTime;
	/**
	 * 结束时间
	 */
	private Long endTime;
	/**
	 * 拍卖产品图片
	 */
	private String imgUrl;
	/**
	 * 拍卖产品id
	 */
	private Long productId;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 当前时间
	 */
	private Long currentTime;
	


	public Long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}

	public Long getTerm() {
		return term;
	}

	public void setTerm(Long term) {
		this.term = term;
	}

	public Double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Double getaPrice() {
		return aPrice;
	}

	public void setaPrice(Double aPrice) {
		this.aPrice = aPrice;
	}

	public Integer getBidNumber() {
		return bidNumber;
	}

	public void setBidNumber(Integer bidNumber) {
		this.bidNumber = bidNumber;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
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

	public void setProductId(Long productId) {
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

	public void setAuctionId(Long auctionId) {
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
