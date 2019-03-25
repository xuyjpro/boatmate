package com.xuyj.boatmate.dao;

import com.xuyj.boatmate.model.ResultBean;

public interface IFeedbackDAO {
	ResultBean post(String token,String feedBack);
	ResultBean get(int currentPage,int pageSize);
}
