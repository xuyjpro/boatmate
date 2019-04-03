package com.xuyj.boatmate.dao;

import com.xuyj.boatmate.model.ResultBean;

public interface IBlogDAO {
	ResultBean getList(int currentPage,int pageSize);
}
