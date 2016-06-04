package com.cn.entity;

public class CartProduct {
	
	private long id;
	private String name;
	private String imgs;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the img
	 */
	public String getImgs() {
		return imgs;
	}
	/**
	 * @param img the img to set
	 */
	public void setImgs(String imgs) {
		if(imgs == null || "".equals(imgs)){
			this.imgs = imgs;
			return;
		}
		String[] imgArr = imgs.split(",");
		if(imgArr.length > 0){
			for (int i = 0; i < imgArr.length; i++) {
				String imgUrl = imgArr[i];
				if(imgUrl.length()<=1){
					continue;
				}else{
					this.imgs = imgArr[i];
					return;
				}
			}
		}
		this.imgs = imgs;
	}
	
}
