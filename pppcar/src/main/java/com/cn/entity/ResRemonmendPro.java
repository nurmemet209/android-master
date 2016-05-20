package com.cn.entity;



public class ResRemonmendPro {

	private long id;//产品ID
	
	private String imgs;//产品图片
	
	private String name;//产品名称
	
	private double retailPrice;//零售价


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	
}
