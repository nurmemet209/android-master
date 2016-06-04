package com.cn.entity;


import android.os.Parcel;
import android.os.Parcelable;

public class FavourableActivityBean implements Parcelable {

    private long activitiId;

    private String actName;//活动名称

    private double minAmount;//订单达金额下限才参加活动

    private double actTypeExtb;//如果是送赠品，该处表示允许的最大数量，0 无数量限制；现金减免，则是减免金额 单位 元； 打折，是折扣值，80 算8折

    private String actRangeName;//优惠范围名称

    private String startTime;//活动开始时间

    private String endTime;//活动结束时间


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getActRangeName() {
        return actRangeName;
    }

    public void setActRangeName(String actRangeName) {
        this.actRangeName = actRangeName;
    }

    public long getActivitiId() {
        return activitiId;
    }

    public void setActivitiId(long activitiId) {
        this.activitiId = activitiId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getActTypeExtb() {
        return actTypeExtb;
    }

    public void setActTypeExtb(double actTypeExtb) {
        this.actTypeExtb = actTypeExtb;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.activitiId);
        dest.writeString(this.actName);
        dest.writeDouble(this.minAmount);
        dest.writeDouble(this.actTypeExtb);
        dest.writeString(this.actRangeName);
        dest.writeString(this.startTime);
        dest.writeString(this.endTime);
    }

    public FavourableActivityBean() {
    }

    protected FavourableActivityBean(Parcel in) {
        this.activitiId = in.readLong();
        this.actName = in.readString();
        this.minAmount = in.readDouble();
        this.actTypeExtb = in.readDouble();
        this.actRangeName = in.readString();
        this.startTime = in.readString();
        this.endTime = in.readString();
    }

    public static final Parcelable.Creator<FavourableActivityBean> CREATOR = new Parcelable.Creator<FavourableActivityBean>() {
        @Override
        public FavourableActivityBean createFromParcel(Parcel source) {
            return new FavourableActivityBean(source);
        }

        @Override
        public FavourableActivityBean[] newArray(int size) {
            return new FavourableActivityBean[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (o instanceof FavourableActivityBean) {
            FavourableActivityBean bean = (FavourableActivityBean) o;
            if (bean.getActivitiId() == this.getActivitiId()) {
                return true;
            }
        }
        return false;
    }
}
