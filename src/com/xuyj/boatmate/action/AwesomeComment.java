package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.ICommentDAO;

public class AwesomeComment extends BaseAction {

	private String token;
	private int id;
	private int isLike;
	@Override
	public String post() {
		// TODO Auto-generated method stub
		ICommentDAO dao=(ICommentDAO) getApplicationContext().getBean("commentDAO");
		if(isLike==0){
			rb=dao.awesomeComment(token, id);
		}else{
			rb=dao.cancelComment(token, id);
		}
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
	public int getIsLike() {
		return isLike;
	}
	public void setIsLike(int isLike) {
		this.isLike = isLike;
	}

}
