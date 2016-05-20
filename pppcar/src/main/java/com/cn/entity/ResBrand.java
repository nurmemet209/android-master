package com.cn.entity;

public class ResBrand {

	private Long id;//品牌id
	
	private String name;//品牌名称

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ResBrand(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ResBrand() {
		super();
	}
	
	
	
}
