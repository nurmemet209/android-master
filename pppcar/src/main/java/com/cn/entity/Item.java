package com.cn.entity;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/3/30.
 */
public class Item {
    private String imgAddress;
    private int type ;
    private int id;
    private String title;
    private float price;
    private long auctionLeftTime;
    private long time;
    private float currentPrice;
    private float fixedPrice;
    private ArrayList<String> bannerAddressList;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
