package com.xuyj.boatmate.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xuyj.boatmate.dao.BaseDAO;
import com.xuyj.boatmate.dao.IFeedbackDAO;
import com.xuyj.boatmate.model.Feedback;
import com.xuyj.boatmate.model.ResultBean;
import com.xuyj.boatmate.model.UserInfo;
import com.xuyj.boatmate.tools.SecretUtils;

public class FeedbackDAO extends BaseDAO implements IFeedbackDAO{

	@Override
	public ResultBean post(String token, String feedBack) {
		// TODO Auto-generated method stub
		
	ResultBean rb=new ResultBean();
		
		int uid;
		try {
			uid = Integer.parseInt(SecretUtils.decode(token));

		} catch (NumberFormatException e) {
			// TODO: handle exception
			rb.setCode(400);
			rb.setMessage("该用户不存在");
			return rb;
		}
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try{
			Feedback feedback=new Feedback();
			feedback.setFeedBack(feedBack);
			UserInfo ui=new UserInfo();
			ui.setId(uid);
			feedback.setUserInfo(ui);
			feedback.setTime(System.currentTimeMillis());
			feedback.setStatus(0);
			session.save(feedback);
			ts.commit();
			rb.setCode(200);
			
		}catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			rb.setCode(400);
			rb.setMessage(e.getMessage());
		}finally {
			session.close();
		}
		return rb;
	}

	@Override
	public ResultBean get(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		ResultBean rb=new ResultBean();

		return rb;

	}

}
