package com.xuyj.boatmate.model;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class UserInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String phone;
	private String nickname;
	private Boolean gender;
	private String birthday;
	private String heartWord;
	private String password;
	private String headPic;

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** minimal constructor */
	public UserInfo(String phone, String password) {
		this.phone = phone;
		this.password = password;
	}

	/** full constructor */
	public UserInfo(String phone, String nickname, Boolean gender, String birthday, String heartWord, String password,
			String headPic) {
		this.phone = phone;
		this.nickname = nickname;
		this.gender = gender;
		this.birthday = birthday;
		this.heartWord = heartWord;
		this.password = password;
		this.headPic = headPic;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Boolean getGender() {
		return this.gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHeartWord() {
		return this.heartWord;
	}

	public void setHeartWord(String heartWord) {
		this.heartWord = heartWord;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeadPic() {
		return this.headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

}