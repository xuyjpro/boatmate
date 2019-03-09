package com.xuyj.boatmate.model;

/**
 * SubComment entity. @author MyEclipse Persistence Tools
 */

public class SubComment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Long time;
	private Integer awesome;
	private String content;
	private Integer parentId;
	private Integer toUid;

	// Constructors

	/** default constructor */
	public SubComment() {
	}

	/** minimal constructor */
	public SubComment(Integer uid, Long time, String content, Integer parentId) {
		this.uid = uid;
		this.time = time;
		this.content = content;
		this.parentId = parentId;
	}

	/** full constructor */
	public SubComment(Integer uid, Long time, Integer awesome, String content, Integer parentId, Integer toUid) {
		this.uid = uid;
		this.time = time;
		this.awesome = awesome;
		this.content = content;
		this.parentId = parentId;
		this.toUid = toUid;
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

	public Integer getToUid() {
		return this.toUid;
	}

	public void setToUid(Integer toUid) {
		this.toUid = toUid;
	}

}