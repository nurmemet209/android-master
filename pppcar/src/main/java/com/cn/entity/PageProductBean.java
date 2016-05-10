package com.cn.entity;

import java.util.List;

@SuppressWarnings("unused")
public class PageProductBean {
	private int page;
	
	private int size;
	
	private int totalPage;
	
	private int totalSize;
	
	private int upPage;
	
	private int nextPage;
	
	private List<ProductBean> productBean;
	
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

	public List<ProductBean> getProductBean() {
		return productBean;
	}

	public void setProductBean(List<ProductBean> productBean) {
		this.productBean = productBean;
	}
	
	
}
