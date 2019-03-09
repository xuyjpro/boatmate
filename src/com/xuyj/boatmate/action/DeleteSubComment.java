package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.ISubCommentDAO;

public class DeleteSubComment extends BaseAction {

	private int id;
	
	@Override
	public String post() {
		// TODO Auto-generated method stub
		ISubCommentDAO dao=(ISubCommentDAO) getApplicationContext().getBean("subCommentDAO");
		rb=dao.deleteSubComment(id);
		
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
