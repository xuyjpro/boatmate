package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.ISchoolHelpDAO;

public class ModifyStatusSchoolHelp extends BaseAction {

	private String token;
	private int id;
	private int status;
	@Override
	public String post() {
		// TODO Auto-generated method stub
		ISchoolHelpDAO dao=(ISchoolHelpDAO) getApplicationContext().getBean("schoolHelpDAO");
		rb=dao.modifyStatus(token, id, status);
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
