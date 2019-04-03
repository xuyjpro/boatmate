package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.IBlogDAO;

public class GetBlogs extends BaseAction {

	private int pageSize=10;
	private int currentPage=1;
	
	@Override
	public String post() {
		// TODO Auto-generated method stub
		IBlogDAO dao=(IBlogDAO) getApplicationContext().getBean("blogDAO");
		rb=dao.getList(currentPage, pageSize);
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

}
