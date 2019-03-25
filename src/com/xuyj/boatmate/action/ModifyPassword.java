package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.IUserInfoDAO;

public class ModifyPassword extends BaseAction {

	private String token;
	private String oldPassword;
	private String newPassword;
	@Override
	public String post() {
		// TODO Auto-generated method stub
		IUserInfoDAO dao=(IUserInfoDAO) getApplicationContext().getBean("userInfoDAO");
		rb=dao.modifyPasswd(token, oldPassword, newPassword);
		return SUCCESS;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
