package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.ISubCommentDAO;

public class GetSubComments extends BaseAction {

	private int currentPage=1;
	private int pageSize=20;
	private int parent_id;
	@Override
	public String post() {
		// TODO Auto-generated method stub
		ISubCommentDAO subCommentDAO=(ISubCommentDAO) getApplicationContext().getBean("subCommentDAO");
		rb=subCommentDAO.getSubComments(parent_id, pageSize, currentPage);
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
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

}
