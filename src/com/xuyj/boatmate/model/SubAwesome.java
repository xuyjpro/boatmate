package com.xuyj.boatmate.model;

/**
 * SubAwesome entity. @author MyEclipse Persistence Tools
 */

public class SubAwesome implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer commentId;
	private Integer uid;

	// Constructors

	/** default constructor */
	public SubAwesome() {
	}

	/** full constructor */
	public SubAwesome(Integer commentId, Integer uid) {
		this.commentId = commentId;
		this.uid = uid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}