package com.xuyj.boatmate.action;

import java.io.File;

import javax.mail.Session;

import com.xuyj.boatmate.dao.impl.AppVersionDAO;
import com.xuyj.boatmate.model.AppVersion;

public class GetAppVersion extends BaseAction {
	

	
	@Override
	public String post() {
		// TODO Auto-generated method stub
		
		AppVersionDAO dao=(AppVersionDAO) getApplicationContext().getBean("appVersionDAO");
		
		rb=dao.getAppVersion();
		
		
		return SUCCESS;
	}

}
