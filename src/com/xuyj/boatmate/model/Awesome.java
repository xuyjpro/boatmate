package com.xuyj.boatmate.model;

/**
 * Awesome entity. @author MyEclipse Persistence Tools
 */

public class Awesome implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer dynamicId;
	private Integer uid;

	// Constructors

	/** default constructor */
	public Awesome() {
	}

	/** full constructor */
	public Awesome(Integer dynamicId, Integer uid) {
		this.dynamicId = dynamicId;
		this.uid = uid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDynamicId() {
		return this.dynamicId;
	}

	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}