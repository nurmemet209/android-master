package com.cn.entity;

import org.dozer.Mapping;
/**
 * 积分产品详情
 * @author chendongdong
 *
 */
public class ResIntegralProductDetail {
	
	private Long id;//产品ID

	private String productNumber;//产品编号
	
	private String name;//产品名称
	
	private String showImg;//产品图片
	
	private String detailImg;//详细图
	
	private String briefDescripe;//产品简介
	
	private Integer integralPrice;//积分价格
	
	private String  typeName;//产品类型
	
	private Integer stockNumber;//库存

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShowImg() {
		return showImg;
	}

	public void setShowImg(String showImg) {
		this.showImg = showImg;
	}

	public Integer getIntegralPrice() {
		return integralPrice;
	}

	public void setIntegralPrice(Integer integralPrice) {
		this.integralPrice = integralPrice;
	}

	public String getDetailImg() {
		return detailImg;
	}

	public void setDetailImg(String detailImg) {
		this.detailImg = detailImg;
	}

	public String getBriefDescripe() {
		return briefDescripe;
	}

	public void setBriefDescripe(String briefDescripe) {
		this.briefDescripe = briefDescripe;
	}

	@Mapping(value="type.name")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
	}
	
}
