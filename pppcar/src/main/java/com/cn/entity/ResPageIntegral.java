package com.cn.entity;

import java.util.List;

@SuppressWarnings("all")
public class ResPageIntegral {
	
	private int page;//当前页
	
	private int size;//每页显示条数
	
	private int totalPage;//总页数
	
	private int totalSize;//总条数
	
	private int upPage;//上一页
	
	private int nextPage;//下一页
	private Integer integral;//积分
	
	private Integer state;//积分状态
	
	private String abortReason;//冻结原因
	
	private List<ResIntegralProduct> resIntegralProducts;//积分产品
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public int getUpPage() {
		return page<=1?1:page-1;
	}

	public void setUpPage(int upPage) {
		this.upPage = upPage;
	}

	public int getNextPage() {
		return page<totalPage?page+1:totalPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
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

	public List<ResIntegralProduct> getResIntegralProducts() {
		return resIntegralProducts;
	}

	public void setResIntegralProducts(List<ResIntegralProduct> resIntegralProducts) {
		this.resIntegralProducts = resIntegralProducts;
	}
}
