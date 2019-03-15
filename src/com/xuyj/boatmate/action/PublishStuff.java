package com.xuyj.boatmate.action;

import java.util.List;

import com.xuyj.boatmate.dao.IStuffDAO;

import java.io.File;
public class PublishStuff extends BaseAction {

	private String token;
	private int category;
	private String content;
	private String title;
	private List<File> pictures;
	private String keyword;
	@Override
	public String post() {
		// TODO Auto-generated method stub
		IStuffDAO dao=(IStuffDAO) getApplicationContext().getBean("stuffDAO");
		rb=dao.publish(token, content, pictures, category, keyword,title);
		return SUCCESS;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<File> getPictures() {
		return pictures;
	}
	public void setPictures(List<File> pictures) {
		this.pictures = pictures;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
