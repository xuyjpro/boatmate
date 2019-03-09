package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.ISubCommentDAO;

public class PublishSubComment extends BaseAction {

	private String token;
	private String content;
	private int parent_id;
	private int to_uid;
	
	@Override
	public String post() {
		// TODO Auto-generated method stub
		ISubCommentDAO subCommentDAO=(ISubCommentDAO) getApplicationContext().getBean("subCommentDAO");
		rb=subCommentDAO.publishSubComment(token, parent_id, content, to_uid);
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

	public int getTo_uid() {
		return to_uid;
	}

	public void setTo_uid(int to_uid) {
		this.to_uid = to_uid;
	}


}
