package com.cn.entity;

public class ResIntegralProduct {
	
	private Long id;//产品ID

	private String productNumber;//产品编号
	
	private String name;//产品名称
	
	private String showImg;//产品图片
	
	private Integer integralPrice;//积分价格

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShowImg() {
		return showImg;
	}

	public void setShowImg(String showImg) {
		this.showImg = showImg;
	}

	public Integer getIntegralPrice() {
		return integralPrice;
	}

	public void setIntegralPrice(Integer integralPrice) {
		this.integralPrice = integralPrice;
	}
	
}
