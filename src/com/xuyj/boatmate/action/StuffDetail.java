package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.IStuffDAO;

public class StuffDetail extends BaseAction{

	private int id;
	
	@Override
	public String post() {
		// TODO Auto-generated method stub
		IStuffDAO dao=(IStuffDAO) getApplicationContext().getBean("stuffDAO");
		rb=dao.getDetail(id);
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
