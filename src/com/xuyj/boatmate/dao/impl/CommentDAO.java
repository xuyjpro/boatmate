package com.xuyj.boatmate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xuyj.boatmate.dao.BaseDAO;
import com.xuyj.boatmate.dao.ICommentDAO;
import com.xuyj.boatmate.dao.impl.DynamicDAO.CusDynamic;
import com.xuyj.boatmate.model.Awesome;
import com.xuyj.boatmate.model.Comment;
import com.xuyj.boatmate.model.Dynamic;
import com.xuyj.boatmate.model.ResultBean;
import com.xuyj.boatmate.model.SubAwesome;
import com.xuyj.boatmate.model.SubComment;
import com.xuyj.boatmate.model.UserInfo;
import com.xuyj.boatmate.tools.SecretUtils;

public class CommentDAO extends BaseDAO implements ICommentDAO {

	@Override
	public ResultBean publishComment(String token, String content, int parent_id) {
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

			Comment comment = new Comment();
			comment.setAwesome(0);
			comment.setComment(0);
			comment.setContent(content);
			comment.setTime(System.currentTimeMillis());

			comment.setParentId(parent_id);
			comment.setUid(uid);
			session.save(comment);

			ts.commit();
			rb.setCode(200);
			rb.setMessage("success");
			rb.setData(comment);

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
	public ResultBean getComments(String token,int parent_id,int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		ResultBean rb = new ResultBean();
		int uid;
		try {
			uid = Integer.parseInt(SecretUtils.decode(token));

		} catch (NumberFormatException e) {
			// TODO: handle exception
			rb.setCode(400);
			rb.setMessage("该身份信息有误");
			return rb;
		}
		if(parent_id<=0){
			rb.setCode(400);
			rb.setMessage("请输入正确动态id");
			return rb;
		}
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			Query query = null;

			query = session.createQuery("from Comment where parent_id=? order by id desc");

			query.setParameter(0, parent_id);
			query.setFirstResult((currentPage - 1) * pageSize);
			query.setMaxResults(pageSize);
			List<Comment> comments = query.list();
			
			List<CusComment> list=new ArrayList<>();
			if(comments!=null){
				for(Comment d:comments){
					CusComment cd=new CusComment();
					cd.setComment(d);
					//是否点赞
					String hql1="select id from SubAwesome where commentId=? and uid=?";
					
					Query query1=session.createQuery(hql1);
					query1.setParameter(0,d.getId());
					query1.setParameter(1,uid);
					query1.setMaxResults(1);
					List list1=query1.list();
					if(list1!=null&&list1.size()!=0){

						cd.setLike(true);
					}
					//头像、名称
					String hql2="from UserInfo where id=?";
					query1=session.createQuery(hql2);
					query1.setParameter(0,d.getUid());
					query1.setMaxResults(1);
					list1.clear();
					list1=query1.list();
					if(list1!=null&&list1.size()!=0){
						UserInfo ui=(UserInfo) list1.get(0);
						cd.setHeadPic(ui.getHeadPic());
						cd.setNickname(ui.getNickname());
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
	public ResultBean deleteComment(String token, int id) {
		// TODO Auto-generated method stub
		
		
		ResultBean rb = new ResultBean();
		int uid;
		try {
			uid = Integer.parseInt(SecretUtils.decode(token));

		} catch (NumberFormatException e) {
			// TODO: handle exception
			rb.setCode(400);
			rb.setMessage("该身份信息有误");
			return rb;
		}
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try{
			
			String hql="delete Comment where id=? and uid=?";
			Query query=session.createQuery(hql);
			query.setParameter(0, id);
			query.setParameter(1, uid);

			query.executeUpdate();
			ts.commit();
			rb.setCode(200);
			rb.setMessage("success");
		}catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			rb.setCode(400);
			rb.setMessage("请求异常");
		}finally {
			session.close();
		}
		return rb;
	}

	@Override
	public ResultBean awesomeComment(String token, int id) {
		// TODO Auto-generated method stub
		ResultBean rb=new ResultBean();
		int uid;
		try {
			uid = Integer.parseInt(SecretUtils.decode(token));

		} catch (NumberFormatException e) {
			// TODO: handle exception
			rb.setCode(400);
			rb.setMessage("该身份信息有误");
			return rb;
		}
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try{
			SubAwesome awesome=new SubAwesome();
			awesome.setCommentId(id);
			awesome.setUid(uid);
			session.save(awesome);
			ts.commit();
			rb.setCode(200);
			rb.setMessage("success");
		}catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			rb.setCode(400);
			rb.setMessage("请求异常");
		}finally {
			session.close();
		}
		return rb;
	}

	@Override
	public ResultBean cancelComment(String token, int id) {
		// TODO Auto-generated method stub
		ResultBean rb=new ResultBean();
		int uid;
		try {
			uid = Integer.parseInt(SecretUtils.decode(token));

		} catch (NumberFormatException e) {
			// TODO: handle exception
			rb.setCode(400);
			rb.setMessage("该身份信息有误");
			return rb;
		}
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try{
		
			String hql="delete SubAwesome where uid=? and comment_id=?";
			Query query=session.createQuery(hql);
			query.setParameter(0, uid);
			query.setParameter(1, id);
			query.executeUpdate();
			ts.commit();
			rb.setCode(200);
			rb.setMessage("success");
		}catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			rb.setCode(400);
			rb.setMessage("请求异常");
		}finally {
			session.close();
		}
		return rb;	
	}
	
	public static class CusComment extends Comment{
		
		private String nickname;
		private String headPic;

		private boolean isLike;
		private String toNickname;
		
		public String getToNickname() {
			return toNickname;
		}

		public void setToNickname(String toNickname) {
			this.toNickname = toNickname;
		}

		public void setComment(Comment d){
			setId(d.getId());
			setUid(d.getUid());
			setAwesome(d.getAwesome());
			setComment(d.getComment());
			setContent(d.getContent());
			setTime(d.getTime());
			setParentId(d.getParentId());
		}

		public void setSubComment(SubComment d){
			setId(d.getId());
			setUid(d.getUid());
			setContent(d.getContent());
			setTime(d.getTime());
			setParentId(d.getParentId());
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getHeadPic() {
			return headPic;
		}

		public void setHeadPic(String headPic) {
			this.headPic = headPic;
		}

		public boolean isLike() {
			return isLike;
		}

		public void setLike(boolean isLike) {
			this.isLike = isLike;
		}
		
	}

	@Override
	public ResultBean commentDetail(String token, int id) {
		// TODO Auto-generated method stub
		ResultBean rb=new ResultBean();
		int uid;
		try {
			uid = Integer.parseInt(SecretUtils.decode(token));

		} catch (NumberFormatException e) {
			// TODO: handle exception
			rb.setCode(400);
			rb.setMessage("该身份信息有误");
			return rb;
		}
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		
		try{
			
			String hql="from Comment where id=?";
			Query query=session.createQuery(hql);
			query.setParameter(0, id);
			query.setMaxResults(1);
			List list=query.list();
			if(list!=null||list.size()!=0){
				CusComment cd=new CusComment();
				Comment c=(Comment) list.get(0);
				cd.setComment(c);
				//是否点赞
				String hql1="select id from SubAwesome where commentId=? and uid=?";
				
				Query query1=session.createQuery(hql1);
				query1.setParameter(0,c.getId());
				query1.setParameter(1,uid);
				query1.setMaxResults(1);
				List list1=query1.list();
				if(list1!=null&&list1.size()!=0){

					cd.setLike(true);
				}
				//头像、名称
				String hql2="from UserInfo where id=?";
				query1=session.createQuery(hql2);
				query1.setParameter(0,c.getUid());
				query1.setMaxResults(1);
				list1.clear();
				list1=query1.list();
				if(list1!=null&&list1.size()!=0){
					UserInfo ui=(UserInfo) list1.get(0);
					cd.setHeadPic(ui.getHeadPic());
					cd.setNickname(ui.getNickname());
				}
				
				
				rb.setCode(200);
				rb.setMessage("success");
				rb.setData(cd);
			}else{
				rb.setCode(400);
				rb.setData("该动态不存在");
			}
			ts.commit();

			
		}catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			rb.setCode(400);
			rb.setMessage("服务器异常");
		}finally {
			session.close();
		}
		return rb;
	}

}
