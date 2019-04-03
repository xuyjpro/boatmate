package com.xuyj.boatmate.dao.impl;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Query;
import org.hibernate.Session;

import com.xuyj.boatmate.dao.BaseDAO;
import com.xuyj.boatmate.dao.IBlogDAO;
import com.xuyj.boatmate.model.ResultBean;

public class BlogDAO extends BaseDAO implements IBlogDAO{

	@Override
	public ResultBean getList(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		ResultBean rb=new ResultBean();
		
		
		Session session=getSession();
		try{
			String hql="from Blog";
			Query query=session.createQuery(hql);
			query.setFirstResult((currentPage-1)*pageSize);
			query.setMaxResults(pageSize);
			List list=query.list();
			rb.setData(list);
			rb.setCode(200);
		}catch (Exception e) {
			// TODO: handle exception
			rb.setCode(400);
			rb.setMessage(e.getMessage());
		}finally {
			session.close();
			return rb;
		}
	}

}
