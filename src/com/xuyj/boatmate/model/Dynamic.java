package com.xuyj.boatmate.model;

/**
 * Dynamic entity. @author MyEclipse Persistence Tools
 */

public class Dynamic implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Integer awesome;
	private Integer comment;
	private String content;
	private Long time;

	// Constructors

	/** default constructor */
	public Dynamic() {
	}

	/** minimal constructor */
	public Dynamic(Integer uid, String content, Long time) {
		this.uid = uid;
		this.content = content;
		this.time = time;
	}

	/** full constructor */
	public Dynamic(Integer uid, Integer awesome, Integer comment, String content, Long time) {
		this.uid = uid;
		this.awesome = awesome;
		this.comment = comment;
		this.content = content;
		this.time = time;
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

	public Integer getAwesome() {
		return this.awesome;
	}

	public void setAwesome(Integer awesome) {
		this.awesome = awesome;
	}

	public Integer getComment() {
		return this.comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

}