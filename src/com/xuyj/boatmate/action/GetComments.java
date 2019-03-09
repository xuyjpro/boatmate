package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.ICommentDAO;

public class GetComments extends BaseAction {

	private String token;
	private int pageSize=20;
	private int currentPage=1;
	private int parent_id;
	@Override
	public String post() {
		// TODO Auto-generated method stub
		ICommentDAO iCommentDAO=(ICommentDAO) getApplicationContext().getBean("commentDAO");
		rb=iCommentDAO.getComments(token,parent_id, currentPage, pageSize);
		return SUCCESS;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
