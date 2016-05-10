package com.cn.entity;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class ResPageIntegral extends BaseEntity{
	

	private Integer integral;//积分
	
	private Integer state;//积分状态
	
	private String abortReason;//冻结原因
	
	private ArrayList<ResIntegralProduct> resIntegralProducts;//积分产品

	@Override
	public long getId() {
		return 0;
	}

	@Override
	public String getName() {
		return null;
	}


	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAbortReason() {
		return abortReason;
	}

	public void setAbortReason(String abortReason) {
		this.abortReason = abortReason;
	}

	public ArrayList<ResIntegralProduct> getResIntegralProducts() {
		return resIntegralProducts;
	}

	public void setResIntegralProducts(ArrayList<ResIntegralProduct> resIntegralProducts) {
		this.resIntegralProducts = resIntegralProducts;
	}
}
