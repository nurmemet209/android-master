package com.cn.entity;



public class ResOrderList {
	
	private Long productId;//产品id
	
	private String imgs;//产品图片
	
	private String name;//产品名称
	
	private Double discount_total_price;//折扣后的总价
	
	private int number;//数量
	
	private Integer integralDeduction;
	
	public Integer getIntegralDeduction() {
		return integralDeduction;
	}
	public void setIntegralDeduction(Integer integralDeduction) {
		this.integralDeduction = integralDeduction;
	}
	
	public Double getDiscount_total_price() {
		return discount_total_price;
	}
	public void setDiscount_total_price(Double discount_total_price) {
		this.discount_total_price = discount_total_price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getImgs() {
		return imgs.split(",")[0];
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    }
