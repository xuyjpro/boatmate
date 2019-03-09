package com.xuyj.boatmate.dao;

import com.xuyj.boatmate.model.ResultBean;

public interface ICommentDAO {

	public ResultBean publishComment(String token,String content,int parent_id);
	public ResultBean getComments(String token,int parentId,int currentPage,int pageSize);
	
	public ResultBean deleteComment(String token,int id);
	public ResultBean awesomeComment(String token,int id);
	public ResultBean cancelComment(String token,int id);
	public ResultBean commentDetail(String token,int  id);
}
