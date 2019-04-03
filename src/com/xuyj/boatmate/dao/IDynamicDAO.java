package com.xuyj.boatmate.dao;

import java.io.File;

import com.xuyj.boatmate.model.ResultBean;

public interface IDynamicDAO {

	public ResultBean getAllDynamics(String token,int category,int currentPage,int pageSize);
	public ResultBean getMainPageDynamics(String token,int to_uid,int currentPage,int pageSize);
	public ResultBean publishDynamic(String token,String content,File picture);
	public ResultBean deleteDynamic(String token,int id);
	public ResultBean awesomeDynamic(String token,int id);
	public ResultBean cancelAwesome(String token,int id);

	public ResultBean dynamicDetail(String token,int id);
}
