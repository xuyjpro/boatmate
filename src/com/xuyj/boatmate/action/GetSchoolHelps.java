package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.ISchoolHelpDAO;

public class GetSchoolHelps extends BaseAction {

	private String token;
	private int currentPage=1;
	private int pageSize=20;
	@Override
	public String post() {
		// TODO Auto-generated method stub
		ISchoolHelpDAO dao=(ISchoolHelpDAO) getApplicationContext().getBean("schoolHelpDAO");
		if(token==null){
			rb=dao.getList(currentPage, pageSize);

		}else{
			rb=dao.getList(token, currentPage, pageSize);
		}
		
		return SUCCESS;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
