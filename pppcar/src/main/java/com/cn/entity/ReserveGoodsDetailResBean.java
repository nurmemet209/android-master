package com.cn.entity;


import java.util.ArrayList;
import java.util.List;

public class ReserveGoodsDetailResBean{
	/**
	 * 个数
	 */
	private int number;
	
	/**
	 * 默认收货地址
	 */
	private Consignee consignee;
	
	private CartProduct bsProduct;
	/**
	 * 积分
	 */
	private int normalIntegral;

	public int getNormalIntegral() {
		return normalIntegral;
	}

	public void setNormalIntegral(int normalIntegral) {
		this.normalIntegral = normalIntegral;
	}

	public CartResBean getCartResBean() {
		return cartResBean;
	}

	public void setCartResBean(CartResBean cartResBean) {
		this.cartResBean = cartResBean;
	}

	public boolean isfullAmount() {
		return isfullAmount;
	}

	private CartResBean cartResBean;

	public ArrayList<FavourableActivityBean> getCoupons() {
		return coupons;
	}

	public void setCoupons(ArrayList<FavourableActivityBean> coupons) {
		this.coupons = coupons;
	}

	private ArrayList<FavourableActivityBean> coupons;

	/**
	 * 是否有发票true无
	 */
	private boolean invoice;
	/**
	 * 总价
	 */
	private double totalPrice;
	/**
	 * 第一次支付
	 */
	private double firstPrice;
	/**
	 * 第二次支付
	 */
	private double lastPrice;
	/**
	 * 实际应付
	 */
	private double meetPrice;
	/***
	 * 优惠金额
	 */
	private double discountPrice;
	/**
	 * 是否全款
	 */
	private boolean isfullAmount;
	
	
	public boolean isIsfullAmount() {
		return isfullAmount;
	}
	public void setIsfullAmount(boolean isfullAmount) {
		this.isfullAmount = isfullAmount;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public Consignee getConsignee() {
		return consignee;
	}
	public void setConsignee(Consignee consignee) {
		this.consignee = consignee;
	}
	public boolean isInvoice() {
		return invoice;
	}
	public void setInvoice(boolean invoice) {
		this.invoice = invoice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public String getTotalPriceStr() {
		return String.valueOf(totalPrice);
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getFirstPrice() {
		return firstPrice;
	}
	public String getFirstPriceStr() {
		return String.valueOf(firstPrice);
	}
	public void setFirstPrice(double firstPrice) {
		this.firstPrice = firstPrice;
	}
	public double getLastPrice() {
		return lastPrice;
	}

	public String getLastPriceStr() {
		return String.valueOf(lastPrice);
	}
	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}
	public double getMeetPrice() {
		return meetPrice;
	}
	public String getMeetPriceStr() {
		return String.valueOf(meetPrice);
	}
	public void setMeetPrice(double meetPrice) {
		this.meetPrice = meetPrice;
	}
	public double getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}
	public CartProduct getBsProduct() {
		return bsProduct;
	}
	public void setBsProduct(CartProduct bsProduct) {
		this.bsProduct = bsProduct;
	}
	

}
