package com.xuyj.boatmate.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xuyj.boatmate.dao.IDynamicDAO;
import com.xuyj.boatmate.model.ResultBean;
import com.xuyj.boatmate.tools.SecretUtils;

public class GetDynamics extends ActionSupport {

	private ResultBean rb;
	private String token;
	private int to_uid;
	private int isAll;
	private int category;//类型，0最新，1最热
	private int currentPage=1;
	private int pageSize=20;
	
	public String post() {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

		IDynamicDAO idDao=(IDynamicDAO) ac.getBean("dynamicDAO");
		
		
		if(isAll==0){
			rb= idDao.getAllDynamics(token,category,currentPage,pageSize);
		}else{
			rb= idDao.getMainPageDynamics(token,to_uid,currentPage,pageSize);
		}
		
		return SUCCESS;
		
	

	}
	public ResultBean getRb() {
		return rb;
	}
	public void setRb(ResultBean rb) {
		this.rb = rb;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTo_uid() {
		return to_uid;
	}
	public void setTo_uid(int to_uid) {
		this.to_uid = to_uid;
	}
	public int getIsAll() {
		return isAll;
	}
	public void setIsAll(int isAll) {
		this.isAll = isAll;
	}
	
	

}
