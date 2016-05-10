package com.cn.entity;

public class ProductBean extends BaseEntity{

	private Long id;
	private String img;
	private String name;
	private Long stockNumber;					/* 库存 */
	private String brandName;//品牌名称
	private String brandType;//品牌类型

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	//private Integer reserve;/*是否可以预定*/
	private float price;

	

	public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(Long stockNumber) {
		this.stockNumber = stockNumber;
	}

	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandType() {
		return brandType;
	}
	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}
	
}
