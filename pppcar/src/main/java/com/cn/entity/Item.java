package com.cn.entity;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/3/30.
 */
public class Item extends BaseEntity{
    private String img;
    private int type ;
    private long id;


    @Override
    public String getName() {
        return name;
    }

    private String name;
    private float price;
    private long auctionLeftTime;
    private long time;
    private float currentPrice;
    private float fixedPrice;
    private ArrayList<String> bannerAddressList;
    /**
     * 起拍价
     */
    private float startPrice;
    /**
     * 最低成交价
     */
    private float minAucitionPrice;
    /**
     * 最小加价幅度
     */
    private float minAddPrice;

    public float getMaxAddPrice() {
        return maxAddPrice;
    }

    public void setMaxAddPrice(float maxAddPrice) {
        this.maxAddPrice = maxAddPrice;
    }

    public float getMinAddPrice() {
        return minAddPrice;
    }

    public void setMinAddPrice(float minAddPrice) {
        this.minAddPrice = minAddPrice;
    }

    public float getMinAucitionPrice() {
        return minAucitionPrice;
    }

    public void setMinAucitionPrice(float minAucitionPrice) {
        this.minAucitionPrice = minAucitionPrice;
    }

    public float getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(float startPrice) {
        this.startPrice = startPrice;
    }

    /**
     * 最大加价幅度
     */
    private float maxAddPrice;

    public ArrayList<String> getBannerAddressList() {
        return bannerAddressList;
    }

    public void setBannerAddressList(ArrayList<String> bannerAddressList) {
        this.bannerAddressList = bannerAddressList;
    }



    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    private String stateStr;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    private String phoneNum;
    /**
     * 出价次数
     */
    private int bidCount;
    private int state;

    public long getAuctionLeftTime() {
        return auctionLeftTime;
    }

    public void setAuctionLeftTime(long auctionLeftTime) {
        this.auctionLeftTime = auctionLeftTime;
    }

    public int getBidCount() {
        return bidCount;
    }

    public void setBidCount(int bidCount) {
        this.bidCount = bidCount;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public float getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(float fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    private int num;

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }

    /**
     * 收藏次数
     */
   private int collectNum;

    public long getId() {
        return id;
    }





    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        if (img==null)
            return "";
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }



    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
