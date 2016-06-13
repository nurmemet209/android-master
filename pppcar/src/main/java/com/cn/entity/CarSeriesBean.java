package com.cn.entity;




public class CarSeriesBean extends BaseEntity{
	
	private long id;
	private String name;
	private String type;

	public CarSeriesBean() {
	}

	public CarSeriesBean(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
