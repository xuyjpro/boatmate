package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.ICommentDAO;

public class PublishComment extends BaseAction {

	private String token;
	private String content;
	private int parent_id;
	@Override
	public String post() {
		// TODO Auto-generated method stub
		
		ICommentDAO iCommentDAO=(ICommentDAO) getApplicationContext().getBean("commentDAO");
		rb=iCommentDAO.publishComment(token, content, parent_id);
		return SUCCESS;
		
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

}
