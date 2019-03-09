package com.xuyj.boatmate.model;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Long time;
	private Integer awesome;
	private Integer comment;
	private String content;
	private Integer parentId;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(Integer uid, Long time, String content, Integer parentId) {
		this.uid = uid;
		this.time = time;
		this.content = content;
		this.parentId = parentId;
	}

	/** full constructor */
	public Comment(Integer uid, Long time, Integer awesome, Integer comment, String content, Integer parentId) {
		this.uid = uid;
		this.time = time;
		this.awesome = awesome;
		this.comment = comment;
		this.content = content;
		this.parentId = parentId;
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

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}