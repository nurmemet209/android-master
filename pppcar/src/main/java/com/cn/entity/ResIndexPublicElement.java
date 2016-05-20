package com.cn.entity;

import java.util.List;

public class ResIndexPublicElement {
	
	private PageResRemonmendPro pageResRemonmendPro;
	
	private List<ResIndexBanner> resIndexBanners;
	
	private List<ResIndexMenu> resIndexMenus;
	
	private List<ResIndexRecommendType> resIndexRecommendTypes;
	
	private List<ResIndexBrandType> resIndexBrandTypes;
	
	private ResAutionProduct resAutionProduct;
	
	private String userName;

	public PageResRemonmendPro getPageResRemonmendPro() {
		return pageResRemonmendPro;
	}

	public void setPageResRemonmendPro(PageResRemonmendPro pageResRemonmendPro) {
		this.pageResRemonmendPro = pageResRemonmendPro;
	}

	public List<ResIndexBanner> getResIndexBanners() {
		return resIndexBanners;
	}

	public void setResIndexBanners(List<ResIndexBanner> resIndexBanners) {
		this.resIndexBanners = resIndexBanners;
	}

	public List<ResIndexMenu> getResIndexMenus() {
		return resIndexMenus;
	}

	public void setResIndexMenus(List<ResIndexMenu> resIndexMenus) {
		this.resIndexMenus = resIndexMenus;
	}

	public List<ResIndexRecommendType> getResIndexRecommendTypes() {
		return resIndexRecommendTypes;
	}

	public void setResIndexRecommendTypes(List<ResIndexRecommendType> resIndexRecommendTypes) {
		this.resIndexRecommendTypes = resIndexRecommendTypes;
	}

	public List<ResIndexBrandType> getResIndexBrandTypes() {
		return resIndexBrandTypes;
	}

	public void setResIndexBrandTypes(List<ResIndexBrandType> resIndexBrandTypes) {
		this.resIndexBrandTypes = resIndexBrandTypes;
	}

	public ResAutionProduct getResAutionProduct() {
		return resAutionProduct;
	}

	public void setResAutionProduct(ResAutionProduct resAutionProduct) {
		this.resAutionProduct = resAutionProduct;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
