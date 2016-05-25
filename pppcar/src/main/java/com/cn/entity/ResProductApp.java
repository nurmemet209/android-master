package com.cn.entity;

import java.util.List;
import java.util.Map;


/**
 * 产品详情
 *
 * @author chendongdong
 */
public class ResProductApp {

    private long id;//产品id

    private String imgs;//产品图片

    private String name;//产品名称

    private String briefDescribe;// 产品简介

    private double retailPrice;    //零售价

    private double tradePrice;// 批发价

    private double activityPrice; //活动价

    private String brandName;// 产品所属品牌 名称

    private String manufacturingCode;// 出厂编号

    private String stockNumber;// 库存

    /**
     * 是否有预定规则
     */
    private Boolean isFlagGoodsCycle;

    private Boolean isFlagGroup;//是否有优惠套餐 true、表示有 false、表示没有

    private Boolean isFlagProperty;//是否有属性规格 true、表示有 false、表示没有

    private Boolean isFlagBorC;//true、是B端，false、是C端

    private Boolean isFlagFavorites;//是否已经收藏  true、表示是 false、表示否

    private Map<String, Map<String, ProductAttrBean>> productAttrs;//属性规格

    private List<ResGroupApp> resGroupApps;//优惠套餐

    private List<ReserveGoodsRuleResBean> reserveGoodsRuleResBeans;//预定方案

    public long getId() {
        return id;
    }

    public void setId(long id) {
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


    public double getTradePrice() {
        return tradePrice;
    }


    public String getManufacturingCode() {
        return manufacturingCode;
    }

    public void setManufacturingCode(String manufacturingCode) {
        this.manufacturingCode = manufacturingCode;
    }

    public String getStockNumber() {
//		int stockNum=Integer.parseInt(stockNumber);
//		stockNumber=stockNum>0?"现货":"缺货";
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

    public Boolean getIsFlagGoodsCycle() {
        return isFlagGoodsCycle;
    }

    public void setIsFlagGoodsCycle(Boolean isFlagGoodsCycle) {
        this.isFlagGoodsCycle = isFlagGoodsCycle;
    }

    public String getBriefDescribe() {
        return briefDescribe;
    }

    public void setBriefDescribe(String briefDescribe) {
        this.briefDescribe = briefDescribe;
    }

    public double getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(double activityPrice) {
        this.activityPrice = activityPrice;
    }


    public Map<String, Map<String, ProductAttrBean>> getProductAttrs() {
        return productAttrs;
    }

    public void setProductAttrs(Map<String, Map<String, ProductAttrBean>> productAttrs) {
        this.productAttrs = productAttrs;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public void setTradePrice(double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public List<ResGroupApp> getResGroupApps() {
        return resGroupApps;
    }

    public void setResGroupApps(List<ResGroupApp> resGroupApps) {
        this.resGroupApps = resGroupApps;
    }

    public List<ReserveGoodsRuleResBean> getReserveGoodsRuleResBeans() {
        return reserveGoodsRuleResBeans;
    }

    public void setReserveGoodsRuleResBeans(List<ReserveGoodsRuleResBean> reserveGoodsRuleResBeans) {
        this.reserveGoodsRuleResBeans = reserveGoodsRuleResBeans;
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


    public boolean hasStock() {
        if ("缺货".equals(stockNumber)) {
            return false;
        }
        return true;
    }


}
