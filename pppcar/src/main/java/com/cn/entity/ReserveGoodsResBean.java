package com.cn.entity;


public class ReserveGoodsResBean {
	/**
	 * 方案描述
	 */
	private String ruleDesc;
	/**
	 * 方案id
	 */
	private long ruleId;

	/**
	 * 是否全款
	 */
	private boolean isfullAmount;
	/**
	 * 总价
	 */
	private double totalPrice;
	/**
	 * 优惠价
	 */
	private double preferentialPrice;
	/**
	 * 第一次支付
	 */
	private double firstPrice;
	/**
	 * 第二次支付
	 */
	private double lastPrice;
	/**
	 * 本次应付
	 */
	private double meetPrice;
	public String getRuleDesc() {
		return ruleDesc;
	}
	
	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}
	public long getRuleId() {
		return ruleId;
	}

	public void setRuleId(long ruleId) {
		this.ruleId = ruleId;
	}
	
	public boolean isIsfullAmount() {
		return isfullAmount;
	}

	public void setIsfullAmount(boolean isfullAmount) {
		this.isfullAmount = isfullAmount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getPreferentialPrice() {
		return preferentialPrice;
	}

	public void setPreferentialPrice(double preferentialPrice) {
		this.preferentialPrice = preferentialPrice;
	}

	public double getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(double firstPrice) {
		this.firstPrice = firstPrice;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public double getMeetPrice() {
		return meetPrice;
	}

	public void setMeetPrice(double meetPrice) {
		this.meetPrice = meetPrice;
	}

}
