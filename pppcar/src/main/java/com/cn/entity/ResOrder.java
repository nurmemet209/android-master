package com.cn.entity;

import java.util.List;



/**
 * 个人中心-我的订单
 * @author chendongdong
 *
 */
@SuppressWarnings("all")
public class ResOrder{
	
	private Long id; //订单Id
	
	private Integer productNum;//订单产品数量
	
	private double totalPrice;//订单总价
	
	private String state;
	
	private String payWay;
	
	private List<ResOrderProduct> orderProducts;//订单产品
	
	private String orderType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}


	public List<ResOrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<ResOrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	
}
