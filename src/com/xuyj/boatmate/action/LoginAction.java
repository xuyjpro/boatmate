package com.xuyj.boatmate.action;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xuyj.boatmate.dao.IUserInfoDAO;
import com.xuyj.boatmate.model.ResultBean;
import com.xuyj.boatmate.model.UserInfo;
import com.xuyj.boatmate.model.UserInfoWithToken;
import com.xuyj.boatmate.tools.SecretUtils;

public class LoginAction extends BaseAction {
	private ResultBean rb;

	private String phone;
	private String password;

	public String post()  {
		// TODO Auto-generated method stub

		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		IUserInfoDAO iuserDao = (IUserInfoDAO) appContext.getBean("userInfoDAO");
		rb = iuserDao.userLogin(phone, password);

		log(phone+","+password);
	
		return SUCCESS;

	}

	public ResultBean getRb() {
		return rb;
	}

	public void setRb(ResultBean rb) {
		this.rb = rb;
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
