package com.cn.entity;


public class ResIndexBrandTypeProduct {
	private Long id;
	private String name;//产品名字
	private Double retailPrice;	//零售价
	private String imgs;//图片
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}
	public String getImgs() {
		return imgs.split(",")[0];
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
}
