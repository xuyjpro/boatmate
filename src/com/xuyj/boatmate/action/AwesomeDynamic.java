package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.IDynamicDAO;

public class AwesomeDynamic extends BaseAction {

	private String token;
	private int id;
	private int isLike;	//0点赞 1取消
	@Override
	public String post() {
		// TODO Auto-generated method stub
		IDynamicDAO dao=(IDynamicDAO) getApplicationContext().getBean("dynamicDAO");
		if(isLike==0){
			rb=dao.awesomeDynamic(token, id);

		}else{
			rb=dao.cancelAwesome(token, id);
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
