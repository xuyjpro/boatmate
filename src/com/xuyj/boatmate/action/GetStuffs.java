package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.IStuffDAO;

public class GetStuffs extends BaseAction {

	private String token;
	private int currentPage=1;
	private int pageSize=20;
	private int category;
	@Override
	public String post() {
		// TODO Auto-generated method stub
		IStuffDAO dao=(IStuffDAO) getApplicationContext().getBean("stuffDAO");
		if(token==null){
			rb=dao.getList(currentPage, pageSize, category);
		}else{
			rb=dao.getList(token,currentPage, pageSize, category);

		}
		return SUCCESS;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}

}
