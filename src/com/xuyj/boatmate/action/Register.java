package com.xuyj.boatmate.action;

import org.springframework.context.ApplicationContext;

import com.xuyj.boatmate.dao.IUserInfoDAO;
import com.xuyj.boatmate.model.ResultBean;

public class Register extends BaseAction {

	
	private String phone;
	private String password;
	
	
	@Override
	public String post() {
		// TODO Auto-generated method stub
		ApplicationContext ac=getApplicationContext();
		IUserInfoDAO userInfoDAO=(IUserInfoDAO) ac.getBean("userInfoDAO");
		rb=userInfoDAO.userRegister(phone, password);
		return SUCCESS;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

}
