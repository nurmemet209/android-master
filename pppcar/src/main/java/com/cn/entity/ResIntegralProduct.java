package com.cn.entity;

public class ResIntegralProduct extends BaseEntity {

	public void setId(long id) {
		this.id = id;
	}

	private long id;



	
	private String name;//产品名称
	
	private String showImg;//产品图片
	
	private Integer integralPrice;//积分价格

	@Override
	public long getId() {
		return id;
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
