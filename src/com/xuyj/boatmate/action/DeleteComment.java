package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.ICommentDAO;

public class DeleteComment extends BaseAction {

	private String token;
	private int id;
	@Override
	public String post() {
		// TODO Auto-generated method stub
		ICommentDAO dao=(ICommentDAO) getApplicationContext().getBean("commentDAO");
		rb=dao.deleteComment(token, id);
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
