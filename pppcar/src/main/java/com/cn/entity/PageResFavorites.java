package com.cn.entity;

import java.util.List;

@SuppressWarnings("all")
public class PageResFavorites extends BaseEntity{

	private List<ResFavorites> favorites;

	public List<ResFavorites> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<ResFavorites> favorites) {
		this.favorites = favorites;
	}


	@Override
	public long getId() {
		return 0;
	}

	@Override
	public String getName() {
		return "";
	}
}
