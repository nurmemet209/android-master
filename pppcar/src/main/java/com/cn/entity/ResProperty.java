package com.cn.entity;



public class ResProperty extends BaseEntity{

	private Long id;
	
	private String propertyName;
	
	private String propertyValue;
	
	private Integer isShow;


	@Override
	public long getId() {
		return 0;
	}

	@Override
	public String getName() {
		return propertyValue;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	
	
}
