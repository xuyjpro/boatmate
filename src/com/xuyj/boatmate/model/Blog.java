package com.xuyj.boatmate.model;

/**
 * Blog entity. @author MyEclipse Persistence Tools
 */

public class Blog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String summary;
	private String descrip;
	private String url;

	// Constructors

	/** default constructor */
	public Blog() {
	}

	/** full constructor */
	public Blog(String title, String summary, String descrip, String url) {
		this.title = title;
		this.summary = summary;
		this.descrip = descrip;
		this.url = url;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescrip() {
		return this.descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}