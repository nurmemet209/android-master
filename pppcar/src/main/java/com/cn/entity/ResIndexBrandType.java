package com.cn.entity;

import java.util.List;



public class ResIndexBrandType {
	
	private String title;//标题
	
	private ResIndexBrandTypeProduct bsProduct;//产品
	
	private String imgUrl;//图片url
	
	private String imgLinkUrl;//图片点击跳转地址
	
	private List<ResIndexBrandList> indexBrandLists;//app首页纵向推荐品牌

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ResIndexBrandTypeProduct getBsProduct() {
		return bsProduct;
	}

	public void setBsProduct(ResIndexBrandTypeProduct bsProduct) {
		this.bsProduct = bsProduct;
	}


	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgLinkUrl() {
		return imgLinkUrl;
	}

	public void setImgLinkUrl(String imgLinkUrl) {
		this.imgLinkUrl = imgLinkUrl;
	}

	public List<ResIndexBrandList> getIndexBrandLists() {
		return indexBrandLists;
	}

	public void setIndexBrandLists(List<ResIndexBrandList> indexBrandLists) {
		this.indexBrandLists = indexBrandLists;
	}

	@Override
	public String toString() {
		return "ResIndexBrandType [title=" + title + ", bsProduct=" + bsProduct + ", imgUrl=" + imgUrl + ", imgLinkUrl="
				+ imgLinkUrl + ", indexBrandLists=" + indexBrandLists + "]";
	}
	
	
}
