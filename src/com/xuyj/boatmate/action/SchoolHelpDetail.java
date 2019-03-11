package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.ISchoolHelpDAO;

public class SchoolHelpDetail extends BaseAction {

	private int id;
	
	@Override
	public String post() {
		// TODO Auto-generated method stub
		ISchoolHelpDAO dao=(ISchoolHelpDAO) getApplicationContext().getBean("schoolHelpDAO");
		rb=dao.detail(id);
		return SUCCESS;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
