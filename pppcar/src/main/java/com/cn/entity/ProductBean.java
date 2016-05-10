package com.cn.entity;

import org.dozer.Mapping;

public class ProductBean {

	private Long id;
	private String img;
	private String name;
	private Long stockNumber;					/* 库存 */
	private String brandName;//品牌名称
	private String brandType;//品牌类型
	//private Integer reserve;/*是否可以预定*/
	
	@Mapping(value="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Mapping(value="imgs")
	public String getImg() {
		return img.split(".")[0];
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Mapping(value="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Mapping(value="stockNumber")
	public Long getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(Long stockNumber) {
		this.stockNumber = stockNumber;
	}
	@Mapping(value="brand.name")
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	@Mapping(value="brand.type")
	public String getBrandType() {
		return brandType;
	}
	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}
	
}
