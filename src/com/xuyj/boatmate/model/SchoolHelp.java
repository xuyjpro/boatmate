package com.xuyj.boatmate.model;

/**
 * SchoolHelp entity. @author MyEclipse Persistence Tools
 */

public class SchoolHelp implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Long time;
	private String title;
	private String content;
	private Integer browser;
	private Integer bounty;
	private Integer status;
	private String picture;
	private Integer postUid;

	// Constructors

	/** default constructor */
	public SchoolHelp() {
	}

	/** minimal constructor */
	public SchoolHelp(Integer uid, Long time, String title, String content) {
		this.uid = uid;
		this.time = time;
		this.title = title;
		this.content = content;
	}

	/** full constructor */
	public SchoolHelp(Integer uid, Long time, String title, String content, Integer browser, Integer bounty,
			Integer status, String picture, Integer postUid) {
		this.uid = uid;
		this.time = time;
		this.title = title;
		this.content = content;
		this.browser = browser;
		this.bounty = bounty;
		this.status = status;
		this.picture = picture;
		this.postUid = postUid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getBrowser() {
		return this.browser;
	}

	public void setBrowser(Integer browser) {
		this.browser = browser;
	}

	public Integer getBounty() {
		return this.bounty;
	}

	public void setBounty(Integer bounty) {
		this.bounty = bounty;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getPostUid() {
		return this.postUid;
	}

	public void setPostUid(Integer postUid) {
		this.postUid = postUid;
	}

}