package com.xuyj.boatmate.action;

import java.io.File;

import org.springframework.context.ApplicationContext;

import com.xuyj.boatmate.dao.IDynamicDAO;

public class PublishDynamic extends BaseAction {

	private String token;
	private String content;
	private File picture;
	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	@Override
	public String post() {
		// TODO Auto-generated method stub
		ApplicationContext ac=getApplicationContext();
		IDynamicDAO dynamicDAO=(IDynamicDAO) ac.getBean("dynamicDAO");
		rb=dynamicDAO.publishDynamic(token, content,picture);
		
		
		return SUCCESS;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
