package com.cn.entity;

import java.util.Date;



@SuppressWarnings("all")
public class ResAutionProduct {

	
	private Long id;//拍卖活动Id
	
	private String imgs;//产品图片
	
	private String name;//产品名称
	
	private Integer state;//活动状态
	
	private Double initPrice;//起拍价
	
	private Date startTime;//开始时间
	
	private Date endTime;//结束时间
	
	private Date nowTime;//结束时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getImgs() {
		return imgs.split(",")[0];
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Double getInitPrice() {
		return initPrice;
	}

	public void setInitPrice(Double initPrice) {
		this.initPrice = initPrice;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getNowTime() {
		return nowTime=new Date();
	}

	public void setNowTime(Date nowTime) {
		this.nowTime = nowTime;
	}
	
}
