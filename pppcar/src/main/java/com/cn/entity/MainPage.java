package com.cn.entity;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/3/30.
 */
public class MainPage extends Message{


    /**
     * 轮播广告
     */
    private ArrayList<Item> bannerImages;
    /**
     * 趴趴头条
     */
    private ArrayList<Item> papaHeadLines;

    private ArrayList<Item> universalItems;
    /**
     * 排气
     */
    private ArrayList<Item> exhaustList;
    /**
     * 避震
     */
    private ArrayList<Item> shockAbsorber;
    /**
     * 轮毂
     */
    private ArrayList<Item> hobList;
    /**
     * 热门推荐
     */
    private ArrayList<Item> hotRecommand;

    public ArrayList<Item> getBannerImages() {
        return bannerImages;
    }

    public void setBannerImages(ArrayList<Item> bannerImages) {
        this.bannerImages = bannerImages;
    }

    public ArrayList<Item> getExhaustList() {
        return exhaustList;
    }

    public void setExhaustList(ArrayList<Item> exhaustList) {
        this.exhaustList = exhaustList;
    }

    public ArrayList<Item> getHobList() {
        return hobList;
    }

    public void setHobList(ArrayList<Item> hobList) {
        this.hobList = hobList;
    }

    public ArrayList<Item> getHotRecommand() {
        return hotRecommand;
    }

    public void setHotRecommand(ArrayList<Item> hotRecommand) {
        this.hotRecommand = hotRecommand;
    }

    public ArrayList<Item> getPapaHeadLines() {
        return papaHeadLines;
    }

    public void setPapaHeadLines(ArrayList<Item> papaHeadLines) {
        this.papaHeadLines = papaHeadLines;
    }

    public ArrayList<Item> getShockAbsorber() {
        return shockAbsorber;
    }

    public void setShockAbsorber(ArrayList<Item> shockAbsorber) {
        this.shockAbsorber = shockAbsorber;
    }

    public ArrayList<Item> getUniversalItems() {
        return universalItems;
    }

    public void setUniversalItems(ArrayList<Item> universalItems) {
        this.universalItems = universalItems;
    }


}
