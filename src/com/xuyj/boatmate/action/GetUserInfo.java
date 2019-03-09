package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.IUserInfoDAO;

public class GetUserInfo extends BaseAction {

	private String token;
	@Override
	public String post() {
		// TODO Auto-generated method stub
		
		
		IUserInfoDAO infoDAO=(IUserInfoDAO) getApplicationContext().getBean("userInfoDAO");
		rb=infoDAO.getUserInfo(token);
		return SUCCESS;
		
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
