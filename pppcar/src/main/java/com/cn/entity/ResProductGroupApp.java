package com.cn.entity;

/**
 * 套餐产品
 * @author chendongdong
 *
 */
public class ResProductGroupApp {

	private Long id;//产品id
	
	private String imgs;//产品图片
	
	private String name;//产品名称
	
	private double retailPrice;	//零售价
	
	private double tradePrice;// 批发价

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	private int productNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(double tradePrice) {
		this.tradePrice = tradePrice;
	}
	
}
