package com.cn.entity;

import java.util.List;




public class CarSeriesBeanChildren extends SectionHeader{
	
	private long id;
	private String name;
	private String type;
	private List<CarSeriesBean> children;
	
	
	public List<CarSeriesBean> getChildren() {
		return children;
	}
	
	public void setChildren(List<CarSeriesBean> children) {
		this.children = children;
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
