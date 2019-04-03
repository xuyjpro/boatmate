package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.IUserInfoDAO;

public class GetUserInfo extends BaseAction {

	private String token;
	private String phone;
	@Override
	public String post() {
		// TODO Auto-generated method stub
		
		
		IUserInfoDAO infoDAO=(IUserInfoDAO) getApplicationContext().getBean("userInfoDAO");
		if(phone!=null){
			rb=infoDAO.getUserInfoByPhone(phone);
		}else{
			rb=infoDAO.getUserInfo(token);

		}
		return SUCCESS;
		
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
