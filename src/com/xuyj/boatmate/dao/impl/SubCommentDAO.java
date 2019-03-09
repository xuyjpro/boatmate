package com.xuyj.boatmate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xuyj.boatmate.dao.BaseDAO;
import com.xuyj.boatmate.dao.ISubCommentDAO;
import com.xuyj.boatmate.dao.impl.CommentDAO.CusComment;
import com.xuyj.boatmate.model.Comment;
import com.xuyj.boatmate.model.ResultBean;
import com.xuyj.boatmate.model.SubComment;
import com.xuyj.boatmate.model.UserInfo;
import com.xuyj.boatmate.tools.SecretUtils;

public class SubCommentDAO extends BaseDAO implements ISubCommentDAO {

	@Override
	public ResultBean publishSubComment(String token, int parent_id, String content, int to_uid) {
		// TODO Auto-generated method stub

		ResultBean rb = new ResultBean();
		if (token == null || content == null || parent_id == 0) {
			rb.setCode(400);
			rb.setMessage("参数不完整");
			return rb;
		}
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		int uid;
		try {
			uid = Integer.parseInt(SecretUtils.decode(token));

		} catch (NumberFormatException e) {
			// TODO: handle exception
			rb.setCode(400);
			rb.setMessage("该用户不存在");
			return rb;
		}
		try {

			SubComment subComment = new SubComment();
			subComment.setAwesome(0);
			subComment.setContent(content);
			subComment.setTime(System.currentTimeMillis());

			subComment.setParentId(parent_id);
			subComment.setUid(uid);

			if (to_uid > 0) {
				subComment.setToUid(to_uid);
			}
			session.save(subComment);

			ts.commit();
			rb.setCode(200);
			rb.setMessage("success");
			rb.setData(subComment);

		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			rb.setCode(400);
			rb.setMessage("请求异常");
		} finally {
			session.close();
		}

		return rb;
	}

	@Override
	public ResultBean getSubComments(int parent_id, int pageSize, int currentPage) {
		// TODO Auto-generated method stub
		ResultBean rb = new ResultBean();
		if (parent_id <= 0) {
			rb.setCode(400);
			rb.setMessage("请输入正确动态id");
			return rb;
		}
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			Query query = null;

			query = session.createQuery("from SubComment where parent_id=? order by id desc");

			query.setParameter(0, parent_id);
			query.setFirstResult((currentPage - 1) * pageSize);
			query.setMaxResults(pageSize);
			List<SubComment> comments = query.list();

			List<CusComment> list = new ArrayList<>();
			if (comments != null) {
				for (SubComment d : comments) {
					CusComment cd=new CusComment();
					cd.setSubComment(d);
					// 头像、名称
					String hql2 = "from UserInfo where id=?";
					Query query1 = session.createQuery(hql2);
					query1.setParameter(0, d.getUid());
					query1.setMaxResults(1);
					List list1 = query1.list();
					if (list1 != null && list1.size() != 0) {
						UserInfo ui = (UserInfo) list1.get(0);
						cd.setHeadPic(ui.getHeadPic());
						cd.setNickname(ui.getNickname());
					}
					if(d.getToUid()!=null){
						// 头像、名称
						String hql3 = "from UserInfo where id=?";
						query1 = session.createQuery(hql3);
						query1.setParameter(0, d.getToUid());
						query1.setMaxResults(1);
						list1.clear();
						list1 = query1.list();
						if (list1 != null && list1.size() != 0) {
							UserInfo ui = (UserInfo) list1.get(0);
							cd.setToNickname(ui.getNickname());
						}
					}
					list.add(cd);
				}
			}
			rb.setCode(200);
			rb.setMessage("success");
			rb.setData(list);
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
		} finally {
			session.close();

		}
		return rb;
	}

	@Override
	public ResultBean deleteSubComment(int id) {
		// TODO Auto-generated method stub
		ResultBean rb = new ResultBean();
		
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "delete SubComment where id=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, id);
			query.executeUpdate();

			ts.commit();
			rb.setCode(200);
			rb.setMessage("success");

		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			rb.setCode(400);
			rb.setMessage("删除失败，请刷新重试！");
		} finally {
			session.close();
		}
		return rb;
	}

}
