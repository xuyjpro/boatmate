package com.xuyj.boatmate.dao;

import com.xuyj.boatmate.model.ResultBean;

public interface ISubCommentDAO {

	public ResultBean publishSubComment(String token,int parent_id,String content,int to_uid);
	public ResultBean getSubComments(int parent_id,int pageSize,int currentPage);
	public ResultBean deleteSubComment(int id);
		
}
