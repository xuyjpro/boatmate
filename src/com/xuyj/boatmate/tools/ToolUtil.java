package com.xuyj.boatmate.tools;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


public class ToolUtil {


	
	public static String getWebRootSubDir(String dir){
		HttpServletRequest request=ServletActionContext.getRequest();
		return request.getSession().getServletContext().getRealPath(dir);
	}

}
