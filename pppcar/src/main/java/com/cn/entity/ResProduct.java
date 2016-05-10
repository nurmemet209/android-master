package com.cn.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品详情
 * @author chendongdong
 *
 */
public class ResProduct extends BaseEntity{
	
	private Long id;//产品id
	
//	private String productNumber;//产品sku 
	
	private String imgs;//产品图片
	
	private String name;//产品名称
	
	private double retailPrice;	//零售价
	
	private double tradePrice;// 批发价
	
	private String brandName;// 产品所属品牌 名称	
	
	private String manufacturingCode;// 出厂编号 
	
	private String stockNumber;// 库存 
	
	private ArrayList<ResProperty> resProperties;//当前产品属性
	
	private Integer orderGoodsCycle=0;//预定规则，付款后几天之内发货
	
	private Integer isFlagGroup;//是否有优惠套餐 0、表示有 1、表示没有
	
	private Integer isFlagProperty;//是否有属性规格 0、表示有 1、表示没有
	
	private Integer isFlagBorC;//0、是B端，1、是C端
	
	private Integer isFlagFavorites;//是否已经收藏  0、表示是 1、表示否
	
	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public String getProductNumber() {
//		return productNumber;
//	}
//
//	public void setProductNumber(String productNumber) {
//		this.productNumber = productNumber;
//	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(double tradePrice) {
		this.tradePrice = tradePrice;
	}


	public String getManufacturingCode() {
		return manufacturingCode;
	}

	public void setManufacturingCode(String manufacturingCode) {
		this.manufacturingCode = manufacturingCode;
	}

	public String getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(String stockNumber) {
		this.stockNumber = stockNumber;
	}


	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public ArrayList<ResProperty> getResProperties() {
		return resProperties;
	}

	public void setResProperties(ArrayList<ResProperty> resProperties) {
		this.resProperties = resProperties;
	}

	public Integer getOrderGoodsCycle() {
		return orderGoodsCycle;
	}

	public void setOrderGoodsCycle(Integer orderGoodsCycle) {
		this.orderGoodsCycle = orderGoodsCycle;
	}

	public Integer getIsFlagGroup() {
		return isFlagGroup;
	}

	public void setIsFlagGroup(Integer isFlagGroup) {
		this.isFlagGroup = isFlagGroup;
	}

	public Integer getIsFlagProperty() {
		return isFlagProperty;
	}

	public void setIsFlagProperty(Integer isFlagProperty) {
		this.isFlagProperty = isFlagProperty;
	}

	public Integer getIsFlagBorC() {
		return isFlagBorC;
	}

	public void setIsFlagBorC(Integer isFlagBorC) {
		this.isFlagBorC = isFlagBorC;
	}

	public Integer getIsFlagFavorites() {
		return isFlagFavorites;
	}

	public void setIsFlagFavorites(Integer isFlagFavorites) {
		this.isFlagFavorites = isFlagFavorites;
	}
	
	
}
