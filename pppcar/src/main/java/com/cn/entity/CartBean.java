package com.cn.entity;




public class CartBean {

	private long id;
	private int number; //购买数量
	private CartProduct bsProduct;
	private boolean checked; /* 1代表选中  ，0代表未选中*/
	
	private double discountPrice;//折扣单价
	private double totalDiscountPrice;//折扣总价
	private double discount;//折扣
	private long tenantId;
	
	
	
	/**
	 * @return the tenantId
	 */
	public long getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(long tenantId) {
		this.tenantId = tenantId;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * @return the bsProduct
	 */
	public CartProduct getBsProduct() {
		return bsProduct;
	}
	/**
	 * @param bsProduct the bsProduct to set
	 */
	public void setBsProduct(CartProduct bsProduct) {
		this.bsProduct = bsProduct;
	}
	/**
	 * @return the checked
	 */
	public boolean getChecked() {
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	/**
	 * @return the discountPrice
	 */
	public double getDiscountPrice() {
		return discountPrice;
	}
	/**
	 * @param discountPrice the discountPrice to set
	 */
	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}
	/**
	 * @return the totalDiscountPrice
	 */
	public double getTotalDiscountPrice() {
		return totalDiscountPrice;
	}
	/**
	 * @param totalDiscountPrice the totalDiscountPrice to set
	 */
	public void setTotalDiscountPrice(double totalDiscountPrice) {
		this.totalDiscountPrice = totalDiscountPrice;
	}
	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
}
