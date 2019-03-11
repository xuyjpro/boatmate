package com.xuyj.boatmate.action;

import java.io.File;

import com.xuyj.boatmate.dao.ISchoolHelpDAO;

public class PublishSchoolHelp extends BaseAction {

	private String token;
	private String title;
	private String content;
	private int bounty;
	private File picture;
	@Override
	public String post() {
		// TODO Auto-generated method stub
		ISchoolHelpDAO dao=(ISchoolHelpDAO) getApplicationContext().getBean("schoolHelpDAO");
		rb=dao.publish(token, title, content, bounty, picture);
		return SUCCESS;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBounty() {
		return bounty;
	}
	public void setBounty(int bounty) {
		this.bounty = bounty;
	}
	public File getPicture() {
		return picture;
	}
	public void setPicture(File picture) {
		this.picture = picture;
	}

}
