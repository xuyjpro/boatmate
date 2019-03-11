package com.xuyj.boatmate.dao;

import java.io.File;

import com.xuyj.boatmate.model.ResultBean;

public interface ISchoolHelpDAO {

	public ResultBean publish(String token,String title,String content,int bounty,File picture);
	public ResultBean getList(int currentPage,int pageSize);
	public ResultBean getList(String token,int currentPage,int pageSize);
	public ResultBean detail(int id);
	public ResultBean delete(String token,int id);
	public ResultBean modifyStatus(String token,int id,int status);
}
