package com.cn.entity;



/**
 * 个人中心-我的订单-订单产品
 * @author chendongdong
 *
 */
public class ResOrderProduct{
	
	
	private Long id;//产品ID
	
	private  String imgs;//产品图片
	
	private String name;//产品名称
	
	private Integer proNum;//数量


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImgs() {
		return imgs;
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


	public Integer getProNum() {
		return proNum;
	}

	public void setProNum(Integer proNum) {
		this.proNum = proNum;
	}
	
}
