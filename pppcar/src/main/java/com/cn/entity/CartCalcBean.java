package com.cn.entity;

import java.util.List;


/**
 * 动态计算价格的bean
 * @author zhurui
 *
 */
public class CartCalcBean {

	
	//总的商品折扣价格
	private Double totalAllDiscountPrice;
	private List<CartProDiscount> content;
	
	
	public Double getTotalAllDiscountPrice() {
		return totalAllDiscountPrice;
	}

	public void setTotalAllDiscountPrice(Double totalAllDiscountPrice) {
		this.totalAllDiscountPrice = totalAllDiscountPrice;
	}

	public List<CartProDiscount> getContent() {
		return content;
	}

	public void setContent(List<CartProDiscount> content) {
		this.content = content;
	}


	public static class CartProDiscount{
		
		private Long cartId;
		//购物车中单个商品总价格（乘以数量过后）
		private Double totalDiscountPrice;
		
		
		public Long getCartId() {
			return cartId;
		}
		public void setCartId(Long cartId) {
			this.cartId = cartId;
		}
		public Double getTotalDiscountPrice() {
			return totalDiscountPrice;
		}
		public void setTotalDiscountPrice(Double totalDiscountPrice) {
			this.totalDiscountPrice = totalDiscountPrice;
		}
		
	}
	
}
