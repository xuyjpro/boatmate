package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.ISchoolHelpDAO;

public class DeleteSchoolHelp extends BaseAction {

	private String token;
	private int id;
	
	@Override
	public String post() {
		// TODO Auto-generated method stub
		ISchoolHelpDAO dao=(ISchoolHelpDAO) getApplicationContext().getBean("schoolHelpDAO");
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
