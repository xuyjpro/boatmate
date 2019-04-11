package com.xuyj.boatmate.action;

import java.io.File;

import com.xuyj.boatmate.dao.impl.AppVersionDAO;

public class PublishAppVersion extends BaseAction {

	private int versionCode;
	private String versionName;
	private File apkFile;
	
	@Override
	public String post() {
		// TODO Auto-generated method stub
		AppVersionDAO dao = (AppVersionDAO) getApplicationContext().getBean("appVersionDAO");

		rb = dao.publishVersion(versionCode, versionName, apkFile);
		
		return SUCCESS;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public File getApkFile() {
		return apkFile;
	}

	public void setApkFile(File apkFile) {
		this.apkFile = apkFile;
	}
	

}
