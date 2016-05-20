package com.cn.entity;

import java.util.Set;

/**
 * 套餐分组
 * @author chendongdong
 *
 */
public class ResGroupApp {
	
	private Long id;//套餐ID
	
	private String title;//标题
	
	private Set<ResProductGroupApp> bsProduct;//套餐产品
	private double totalRetailPrice;
	private double totalTradePrice;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<ResProductGroupApp> getBsProduct() {
		return bsProduct;
	}

	public void setBsProduct(Set<ResProductGroupApp> bsProduct) {
		this.bsProduct = bsProduct;
	}

	@Override
	public String toString() {
		if (title!=null){
			return title;
		}
		return super.toString();
	}

	public double getTotalTradePrice() {
		return totalTradePrice;
	}

	public void setTotalTradePrice(double totalTradePrice) {
		this.totalTradePrice = totalTradePrice;
	}

	public double getTotalRetailPrice() {
		return totalRetailPrice;
	}

	public void setTotalRetailPrice(double totalRetailPrice) {
		this.totalRetailPrice = totalRetailPrice;
	}
}
