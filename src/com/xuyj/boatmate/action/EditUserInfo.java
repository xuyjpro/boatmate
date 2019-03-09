package com.xuyj.boatmate.action;

import java.io.File;

import com.xuyj.boatmate.dao.IUserInfoDAO;

public class EditUserInfo extends BaseAction {

	private String token;
	private String nickname;
	private int gender = -1;
	private String birthday;
	private String heartWord;
	private File headPic;
	
	@Override
	public String post() {
		// TODO Auto-generated method stub
		IUserInfoDAO userInfoDAO=(IUserInfoDAO) getApplicationContext().getBean("userInfoDAO");
		rb=userInfoDAO.editUserInfo(token, nickname, gender, birthday, heartWord, headPic);
		return SUCCESS;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHeartWord() {
		return heartWord;
	}

	public void setHeartWord(String heartWord) {
		this.heartWord = heartWord;
	}

	public File getHeadPic() {
		return headPic;
	}

	public void setHeadPic(File headPic) {
		this.headPic = headPic;
	}

}
