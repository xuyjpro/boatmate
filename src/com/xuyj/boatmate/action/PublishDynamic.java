package com.xuyj.boatmate.action;

import org.springframework.context.ApplicationContext;

import com.xuyj.boatmate.dao.IDynamicDAO;

public class PublishDynamic extends BaseAction {

	private String token;
	private String content;
	
	@Override
	public String post() {
		// TODO Auto-generated method stub
		ApplicationContext ac=getApplicationContext();
		IDynamicDAO dynamicDAO=(IDynamicDAO) ac.getBean("dynamicDAO");
		rb=dynamicDAO.publishDynamic(token, content);
		
		
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
