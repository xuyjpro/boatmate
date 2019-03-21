package com.xuyj.boatmate.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xuyj.boatmate.dao.BaseDAO;
import com.xuyj.boatmate.dao.IStuffDAO;
import com.xuyj.boatmate.model.ResultBean;
import com.xuyj.boatmate.model.Stuff;
import com.xuyj.boatmate.model.UserInfo;
import com.xuyj.boatmate.tools.ImageTools;
import com.xuyj.boatmate.tools.SecretUtils;

public class StuffDAO extends BaseDAO implements IStuffDAO {

	/*
	 * (non-Javadoc)
	 * @see com.xuyj.boatmate.dao.IStuffDAO#publish(java.lang.String, java.lang.String, java.util.List, java.lang.String, java.lang.String)
	 * 
	 * @param category  0 寻物 	1 失物		2跳蚤
	 */
	@Override
	public ResultBean publish(String token, String content, List<File> pictures, int category, String keyword,String title,float price) {
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
			Stuff stuff=new Stuff();
			stuff.setUid(uid);
			stuff.setContent(content);
			stuff.setHot(0);
			stuff.setCategory(category);
			stuff.setKeyword(keyword);
			stuff.setTitle(title);
			stuff.setPrice(price);
			stuff.setTime(System.currentTimeMillis());
			if(pictures!=null){
				if(pictures.size()==1){
					long picture1=System.currentTimeMillis();
					ImageTools.saveImage(pictures.get(0), picture1+"");
					stuff.setPicture1(picture1+"");

				}
				if(pictures.size()==2){
					
					long picture1=System.currentTimeMillis();
					ImageTools.saveImage(pictures.get(0), picture1+"");
					stuff.setPicture1(picture1+"");
					
					
					long picture2=System.currentTimeMillis();
					ImageTools.saveImage(pictures.get(1), picture2+"");
					stuff.setPicture2(picture2+"");

				}
			}
			session.save(stuff);
			ts.commit();
			rb.setCode(200);
	
		}catch(Exception e){
			e.printStackTrace();
			ts.rollback();
			rb.setCode(400);
			rb.setMessage(e.getMessage());
		}finally {
			session.close();
		}
		
		
		return rb;
	}

	@Override
	public ResultBean getList(int currentPage, int pageSize, int category) {
		// TODO Auto-generated method stub
		ResultBean rb=new ResultBean();
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		
		try{
			String hql="from Stuff where category=? order by id desc";
			Query query=session.createQuery(hql);
			query.setParameter(0, category);
			query.setFirstResult((currentPage-1)*pageSize);
			query.setMaxResults(pageSize);
			List<Stuff> list=query.list();
			if(list!=null&&list.size()!=0){
				
				List list2=new ArrayList<>();
				
				for(Stuff s:list){
					CusStuff cs=new CusStuff();
					//头像、名称
					String hql2="from UserInfo where id=?";
					query=session.createQuery(hql2);
					query.setParameter(0,s.getUid());
					query.setMaxResults(1);
					
					List list1=query.list();
					if(list1!=null&&list1.size()!=0){
						UserInfo ui=(UserInfo) list1.get(0);
						cs.setHeadPic(ui.getHeadPic());
						cs.setNickname(ui.getNickname());
					}
					cs.setStuff(s);

					list2.add(cs);
				}
				rb.setData(list2);
				
			}
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
	public static class CusStuff{
		private String headPic;
		private String nickname;
		private Stuff stuff;
		
		public Stuff getStuff() {
			return stuff;
		}
		public void setStuff(Stuff stuff) {
			this.stuff = stuff;
		}
		public String getHeadPic() {
			return headPic;
		}
		public void setHeadPic(String headPic) {
			this.headPic = headPic;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		
		
	}
	@Override
	public ResultBean getList(String token, int currentPage, int pageSize, int category) {
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
			String hql="from Stuff where uid=? and category=? order by id desc";
			Query query=session.createQuery(hql);
			query.setParameter(0, uid);

			query.setParameter(1, category);
			
			query.setFirstResult((currentPage-1)*pageSize);
			query.setMaxResults(pageSize);
			List<Stuff> list=query.list();
			if(list!=null&&list.size()!=0){
				
				List list2=new ArrayList<>();
				
				//头像、名称
				String hql2="from UserInfo where id=?";
				query=session.createQuery(hql2);
				query.setParameter(0,((Stuff)list.get(0)).getUid());
				query.setMaxResults(1);
				
				List list1=query.list();
				if(list1!=null&&list1.size()!=0){
					UserInfo ui=(UserInfo) list1.get(0);
					
					
					for(Stuff s:list){
						CusStuff cs=new CusStuff();
						cs.setHeadPic(ui.getHeadPic());
						cs.setNickname(ui.getNickname());
						cs.setStuff(s);
						list2.add(cs);
					}
				}
				
				rb.setData(list2);
				
			}
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
	public ResultBean getDetail(int id) {
		// TODO Auto-generated method stub
		ResultBean rb=new ResultBean();
		Session session = getSession();
		
		try{
		
			String hql="select s.id as id,u.nickname from Stuff s,UserInfo u where s.id=? and s.uid=u.id";
			Query query=session.createQuery(hql);
			query.setParameter(0, id);
			query.setMaxResults(1);
			List list=query.list();
			if(list!=null||list.size()!=0){
				//CusStuff cs=(CusStuff) list.get(0);
				rb.setData(list);
			}
			rb.setCode(200);
		}catch (Exception e) {
			// TODO: handle exception
			rb.setCode(400);
			rb.setMessage(e.getMessage());
		}
		
		return rb;
	}

	@Override
	public ResultBean delete(String token, int id) {
		// TODO Auto-generated method stub
		ResultBean rb=new ResultBean();
		int uid = 0;
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
			String hql="delete Stuff where id=? and uid=?";
			Query query=session.createQuery(hql);
			
			query.setParameter(0, id);
			query.setParameter(1, uid);
			query.executeUpdate();
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
}
