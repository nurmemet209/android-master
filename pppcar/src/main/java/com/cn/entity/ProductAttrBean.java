package com.cn.entity;

import java.util.Set;
/**
 * 产品属性规格
 * @author chendongdong
 *
 */
public class ProductAttrBean {
	private String state ;
	private Set<Long> productId;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Set<Long> getProductId() {
		return productId;
	}
	public void setProductId(Set<Long> productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "ProductAttrBean [state=" + state + ", productId=" + productId+ "]";
	}
	

}
