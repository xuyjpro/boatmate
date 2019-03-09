package com.xuyj.boatmate.action;

import javax.security.auth.login.AppConfigurationEntry;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.xuyj.boatmate.model.ResultBean;

public abstract class BaseAction extends ActionSupport {

	public ResultBean rb;
	public Logger logger=Logger.getLogger(getClass());
	public ApplicationContext getApplicationContext(){
		return new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	public abstract String post();
	public ResultBean getRb() {
		return rb;
	}
	public void setRb(ResultBean rb) {
		this.rb = rb;
	}
	public void log(String msg){
		logger.info(msg);
	}
	
}
