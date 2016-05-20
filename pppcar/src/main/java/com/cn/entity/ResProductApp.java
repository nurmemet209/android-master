package com.cn.entity;

import java.util.List;
import java.util.Map;



/**
 * 产品详情
 * @author chendongdong
 *
 */
public class ResProductApp{

	private Long id;//产品id

	private String imgs;//产品图片
	
	private String name;//产品名称
	
	private String briefDescribe;// 产品简介 
	
	private Double retailPrice;	//零售价
	
	private Double tradePrice;// 批发价
	
	private Double activityPrice; //活动价
	
	private String brandName;// 产品所属品牌 名称	
	
	private String manufacturingCode;// 出厂编号 
	
	private String stockNumber;// 库存 
	
	private List<ResPropertyApp> resProperties;//当前产品属性
	
	private Integer orderGoodsCycle=0;//预定规则，付款后几天之内发货
	
	private Boolean isFlagGroup;//是否有优惠套餐 0、表示有 1、表示没有
	
	private Boolean isFlagProperty;//是否有属性规格 0、表示有 1、表示没有
	
	private Boolean isFlagBorC;//0、是B端，1、是C端
	
	private Boolean isFlagFavorites;//是否已经收藏  0、表示是 1、表示否
	
	private Map<String,Map<String,ProductAttrBean>> productAttrs;//属性规格
	
	private List<ResGroupApp> resGroupApps;//优惠套餐
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<ResPropertyApp> getResProperties() {
		return resProperties;
	}

	public void setResProperties(List<ResPropertyApp> resProperties) {
		this.resProperties = resProperties;
	}

	public Integer getOrderGoodsCycle() {
		return orderGoodsCycle;
	}

	public void setOrderGoodsCycle(Integer orderGoodsCycle) {
		this.orderGoodsCycle = orderGoodsCycle;
	}

	public Boolean getIsFlagGroup() {
		return isFlagGroup;
	}

	public void setIsFlagGroup(Boolean isFlagGroup) {
		this.isFlagGroup = isFlagGroup;
	}

	public Boolean getIsFlagProperty() {
		return isFlagProperty;
	}

	public void setIsFlagProperty(Boolean isFlagProperty) {
		this.isFlagProperty = isFlagProperty;
	}

	public Boolean getIsFlagBorC() {
		return isFlagBorC;
	}

	public void setIsFlagBorC(Boolean isFlagBorC) {
		this.isFlagBorC = isFlagBorC;
	}

	public Boolean getIsFlagFavorites() {
		return isFlagFavorites;
	}

	public void setIsFlagFavorites(Boolean isFlagFavorites) {
		this.isFlagFavorites = isFlagFavorites;
	}

	public String getBriefDescribe() {
		return briefDescribe;
	}

	public void setBriefDescribe(String briefDescribe) {
		this.briefDescribe = briefDescribe;
	}

	public Double getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(Double activityPrice) {
		this.activityPrice = activityPrice;
	}


	public Map<String,Map<String,ProductAttrBean>> getProductAttrs() {
		return productAttrs;
	}

	public void setProductAttrs(Map<String,Map<String,ProductAttrBean>> productAttrs) {
		this.productAttrs = productAttrs;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public List<ResGroupApp> getResGroupApps() {
		return resGroupApps;
	}

	public void setResGroupApps(List<ResGroupApp> resGroupApps) {
		this.resGroupApps = resGroupApps;
	}
	
	
	
}
