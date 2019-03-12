package com.xuyj.boatmate.model;

/**
 * Stuff entity. @author MyEclipse Persistence Tools
 */

public class Stuff implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Long time;
	private String content;
	private String picture1;
	private String picture2;
	private Integer hot;
	private String keyword;
	private Integer category;

	// Constructors

	/** default constructor */
	public Stuff() {
	}

	/** minimal constructor */
	public Stuff(Integer uid, Long time, String content) {
		this.uid = uid;
		this.time = time;
		this.content = content;
	}

	/** full constructor */
	public Stuff(Integer uid, Long time, String content, String picture1, String picture2, Integer hot, String keyword,
			Integer category) {
		this.uid = uid;
		this.time = time;
		this.content = content;
		this.picture1 = picture1;
		this.picture2 = picture2;
		this.hot = hot;
		this.keyword = keyword;
		this.category = category;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicture1() {
		return this.picture1;
	}

	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}

	public String getPicture2() {
		return this.picture2;
	}

	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}

	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

}