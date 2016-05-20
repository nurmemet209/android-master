package com.cn.entity;

import java.util.ArrayList;
import java.util.List;



/**
 * 查询购物车，所需要的数据实体
 * @author zhurui
 *
 */
public class CartResBean {
	
	private List<CartBean> content = new ArrayList<CartBean>();	
	private double totalAllDiscountPrice = 0l;//折扣总价
	private double totalRetailPrice = 0l;//实际总价（零售总价） 
	

	public List<CartBean> getContent() {
		return content;
	}

	public void setContent(List<CartBean> content) {
		this.content = content;
	}

	/**
	 * @return the totalAllDiscountPrice
	 */
	public double getTotalAllDiscountPrice() {
		return totalAllDiscountPrice;
	}

	/**
	 * @param totalAllDiscountPrice the totalAllDiscountPrice to set
	 */
	public void setTotalAllDiscountPrice(double totalAllDiscountPrice) {
		this.totalAllDiscountPrice = totalAllDiscountPrice;
	}

	/**
	 * @return the totalRetailPrice
	 */
	public double getTotalRetailPrice() {
		return totalRetailPrice;
	}

	/**
	 * @param totalRetailPrice the totalRetailPrice to set
	 */
	public void setTotalRetailPrice(double totalRetailPrice) {
		this.totalRetailPrice = totalRetailPrice;
	}

}
