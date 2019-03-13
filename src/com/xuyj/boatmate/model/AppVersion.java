package com.xuyj.boatmate.model;

/**
 * AppVersion entity. @author MyEclipse Persistence Tools
 */

public class AppVersion implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer versionCode;
	private String versionName;
	private String downloadUrl;
	private String apkName;

	// Constructors

	/** default constructor */
	public AppVersion() {
	}

	/** full constructor */
	public AppVersion(Integer versionCode, String versionName, String downloadUrl, String apkName) {
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.downloadUrl = downloadUrl;
		this.apkName = apkName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersionCode() {
		return this.versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return this.versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getDownloadUrl() {
		return this.downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getApkName() {
		return this.apkName;
	}

	public void setApkName(String apkName) {
		this.apkName = apkName;
	}

}