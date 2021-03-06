package com.xuyj.boatmate.dao;

import java.io.File;
import java.util.List;

import com.xuyj.boatmate.model.ResultBean;

public interface IStuffDAO {

	ResultBean publish(String token,String content,List<File> pictures,int  category,String keyword,String title,float price);
	ResultBean getList(int currentPage,int pageSize,int category);
	ResultBean getList(String token,int currentPage,int pageSize,int category);
	ResultBean getDetail(int id);
	ResultBean delete(String token,int id);
	
}
