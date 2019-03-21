package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.IStuffDAO;

public class StuffDelete extends BaseAction{

	private String token;
	private int id;
	@Override
	public String post() {
		// TODO Auto-generated method stub
		IStuffDAO dao=(IStuffDAO) getApplicationContext().getBean("stuffDAO");
		rb=dao.delete(token, id);
		return SUCCESS;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
