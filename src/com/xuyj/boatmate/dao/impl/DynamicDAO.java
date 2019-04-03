package com.xuyj.boatmate.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xuyj.boatmate.dao.BaseDAO;
import com.xuyj.boatmate.dao.IDynamicDAO;
import com.xuyj.boatmate.model.Awesome;
import com.xuyj.boatmate.model.Dynamic;
import com.xuyj.boatmate.model.ResultBean;
import com.xuyj.boatmate.model.UserInfo;
import com.xuyj.boatmate.tools.HibernateUtil;
import com.xuyj.boatmate.tools.ImageTools;
import com.xuyj.boatmate.tools.SecretUtils;

public class DynamicDAO extends BaseDAO implements IDynamicDAO {

	@Override
	public ResultBean getAllDynamics(String token, int category, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		ResultBean rb = new ResultBean();
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

		try {
			Query query = null;
			String hql = "";
			if (category == 0) { // 最新
				hql = "from Dynamic order by id desc";

			} else {// 其他最热
				hql = "from Dynamic  order by awesome+comment desc";

			}
			query = session.createQuery(hql);			

			query.setFirstResult((currentPage - 1) * pageSize);
			query.setMaxResults(pageSize);
			List<Dynamic> dynamics = query.list();
			List<CusDynamic> list=new ArrayList<>();
			if(dynamics!=null){
				for(Dynamic d:dynamics){
					CusDynamic cd=new CusDynamic();
					cd.setDynamic(d);
					//是否点赞
					String hql1="select id from Awesome where dynamicId=? and uid=?";
					
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
			
			ts.commit();

			rb.setCode(200);
			rb.setMessage("success");
			rb.setData(list);
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			rb.setCode(400);
			rb.setMessage(e.getMessage());

		} finally {
			session.close();

		}
		return rb;
	}

	@Override
	public ResultBean getMainPageDynamics(String token, int to_uid, int currentPage, int pageSize) {
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
		try {
			Query query = null;


			query = session.createQuery("from Dynamic where uid=? order by id desc");

			if(to_uid>0){//查别人
				query.setParameter(0, to_uid);
			}else{//查自己
				query.setParameter(0, uid);
			}
			query.setFirstResult((currentPage - 1) * pageSize);
			query.setMaxResults(pageSize);

			List<Dynamic> dynamics = query.list();
			
			List<CusDynamic> list=new ArrayList<>();
			if(dynamics!=null){
				for(Dynamic d:dynamics){
					CusDynamic cd=new CusDynamic();
					cd.setDynamic(d);
					//是否点赞
					String hql1="select id from Awesome where dynamicId=? and uid=?";
					
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
			ts.commit();
			
			rb.setCode(200);
			rb.setData(list);
		
		} catch (Exception e) {
			// TODO: handle exception

			ts.rollback();
			rb.setCode(400);
			rb.setMessage(e.getMessage());

		} finally {
			session.close();
		}
		return rb;
	}

	@Override
	public ResultBean publishDynamic(String token, String content,File picture) {
		// TODO Auto-generated method stub
		ResultBean rb = new ResultBean();

		if (token == null || content == null) {

			rb.setCode(400);
			rb.setMessage("参数不完整");

		} else {

			int uid;
			try {
				uid = Integer.parseInt(SecretUtils.decode(token));

			} catch (NumberFormatException e) {
				// TODO: handle exception
				rb.setCode(400);
				rb.setMessage("该用户不存在");
				return rb;
			}
			String picName=null;
			if(picture!=null){
				picName=System.currentTimeMillis()+"";
				ImageTools.saveImage(picture, picName);
			}
			Session session = getSession();
			Transaction ts = session.beginTransaction();
			try {

				Dynamic dynamic = new Dynamic();

				dynamic.setAwesome(0);
				dynamic.setComment(0);
				dynamic.setUid(uid);
				dynamic.setContent(content);
				dynamic.setTime(System.currentTimeMillis());
				dynamic.setPicture(picName);
				session.save(dynamic);

				ts.commit();
				rb.setCode(200);
				rb.setMessage("success");
				rb.setData(dynamic);
			} catch (Exception e) {
				// TODO: handle exception
				if (ts != null) {
					ts.rollback();
				}
				rb.setCode(400);
				rb.setMessage(e.getMessage());

			} finally {
				if (session != null) {
					session.close();

				}
			}

		}

		return rb;
	}

	@Override
	public ResultBean deleteDynamic(String token, int id) {
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
			
			String hql="delete Dynamic where id=? and uid=?";
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
			rb.setMessage(e.getMessage());
		}finally {
			session.close();
		}
		return rb;
	}

	@Override
	public ResultBean awesomeDynamic(String token, int id) {
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
			Awesome awesome=new Awesome();
			awesome.setDynamicId(id);
			awesome.setUid(uid);
			session.save(awesome);
			ts.commit();
			rb.setCode(200);
			rb.setMessage("success");
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
	public ResultBean cancelAwesome(String token, int id) {
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
		
			String hql="delete Awesome where uid=? and dynamic_id=?";
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
			rb.setMessage(e.getMessage());
		}finally {
			session.close();
		}
		return rb;	
	}
	public static class CusDynamic extends Dynamic{
		// Fields

	
		private String nickname;
		private String headPic;

		private boolean isLike;
		
		
		public boolean isLike() {
			return isLike;
		}
		public void setLike(boolean isLike) {
			this.isLike = isLike;
		}
		
		private void setDynamic(Dynamic d){
//			this.id=d.getId();
//			this.uid = d.getUid();
//			this.awesome = d.getAwesome();
//			this.comment = d.getComment();
//			this.content = d.getContent();
//			this.time = d.getTime();
			setId(d.getId());
			setUid(d.getUid());
			setAwesome(d.getAwesome());
			setComment(d.getComment());
			setContent(d.getContent());
			setTime(d.getTime());
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
		
	}
	@Override
	public ResultBean dynamicDetail(String token,int id) {
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
			
			String hql="from Dynamic where id=?";
			Query query=session.createQuery(hql);
			query.setParameter(0, id);
			query.setMaxResults(1);
			List list=query.list();
			if(list!=null||list.size()!=0){
				CusDynamic cd=new CusDynamic();
				Dynamic d=(Dynamic) list.get(0);
				cd.setDynamic(d);
				//是否点赞
				String hql1="select id from Awesome where dynamicId=? and uid=?";
				
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
				
				
				rb.setCode(200);
				rb.setData(cd);
			}else{
				rb.setCode(400);
				rb.setMessage("该动态不存在");
			}
			ts.commit();

			
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


}
