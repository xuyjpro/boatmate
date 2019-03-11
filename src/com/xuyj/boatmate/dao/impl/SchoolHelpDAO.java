package com.xuyj.boatmate.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xuyj.boatmate.dao.BaseDAO;
import com.xuyj.boatmate.dao.ISchoolHelpDAO;
import com.xuyj.boatmate.model.Dynamic;
import com.xuyj.boatmate.model.ResultBean;
import com.xuyj.boatmate.model.SchoolHelp;
import com.xuyj.boatmate.model.UserInfo;
import com.xuyj.boatmate.tools.SecretUtils;

public class SchoolHelpDAO extends BaseDAO implements ISchoolHelpDAO {

	@Override
	public ResultBean publish(String token, String title, String content, int bounty, File picture) {
		// TODO Auto-generated method stub
		ResultBean rb = new ResultBean();
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

		try {
			SchoolHelp sh = new SchoolHelp();
			sh.setUid(uid);
			sh.setTitle(title);
			sh.setContent(content);
			sh.setTime(System.currentTimeMillis());

			sh.setBounty(bounty);
			session.save(sh);
			ts.commit();
			rb.setCode(200);
			rb.setMessage("success");
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();

			rb.setCode(400);
			rb.setMessage("发布失败");
		} finally {
			session.close();
		}

		return rb;
	}

	@Override
	public ResultBean getList(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		ResultBean rb = new ResultBean();
		Session session = getSession();
		Transaction ts = session.beginTransaction();

		String hql = "from SchoolHelp order by id desc";
		Query query = session.createQuery(hql);
		query.setFirstResult((currentPage - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<SchoolHelp> shs = query.list();

		try {
			if (shs != null) {
				List<CusSchoolHelp> cshs = new ArrayList<>();

				for (SchoolHelp sh : shs) {

					CusSchoolHelp csh = new CusSchoolHelp();
					// 头像、名称
					String hql2 = "from UserInfo where id=?";
					query = session.createQuery(hql2);
					query.setParameter(0, sh.getUid());
					query.setMaxResults(1);

					List list = query.list();
					if (list != null && list.size() != 0) {
						UserInfo ui = (UserInfo) list.get(0);
						csh.setHeadPic(ui.getHeadPic());
						csh.setNickname(ui.getNickname());
					}
					csh.setSchoolHelp(sh);
					cshs.add(csh);
				}
				rb.setData(cshs);
			}
			rb.setCode(200);
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			rb.setCode(400);
			rb.setMessage("查询失败");
		} finally {
			session.close();
		}
		return rb;

	}

	@Override
	public ResultBean getList(String token, int currentPage, int pageSize) {
		// TODO Auto-generated method stub

		ResultBean rb = new ResultBean();

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

		try {
			String hql = "from SchoolHelp where uid=? order by id desc";
			Query query = session.createQuery(hql);
			query.setParameter(0, uid);

			query.setFirstResult((currentPage - 1) * pageSize);
			query.setMaxResults(pageSize);
			List<SchoolHelp> shs = query.list();
			if (shs != null) {
				List<CusSchoolHelp> cshs = new ArrayList<>();

				for (SchoolHelp sh : shs) {

					CusSchoolHelp csh = new CusSchoolHelp();
					// 头像、名称
					String hql2 = "from UserInfo where id=?";
					query = session.createQuery(hql2);
					query.setParameter(0, sh.getUid());
					query.setMaxResults(1);

					List list = query.list();
					if (list != null && list.size() != 0) {
						UserInfo ui = (UserInfo) list.get(0);
						csh.setHeadPic(ui.getHeadPic());
						csh.setNickname(ui.getNickname());
					}
					csh.setSchoolHelp(sh);
					cshs.add(csh);
				}
				rb.setData(cshs);
			}
			rb.setCode(200);
			ts.commit();

		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			rb.setCode(400);
			rb.setMessage("查询失败");
		} finally {
			session.close();
		}
		return rb;

	}

	@Override
	public ResultBean delete(String token, int id) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xuyj.boatmate.dao.ISchoolHelpDAO#modifyStatus(java.lang.String,
	 * int, int)
	 * 
	 * 0 待提交 1提交 2确认 3驳回
	 */

	@Override
	public ResultBean modifyStatus(String token, int id, int status) {
		// TODO Auto-generated method stub
		ResultBean rb = new ResultBean();
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

		try {
			String hql = "from SchoolHelp where id=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, id);
			query.setMaxResults(1);
			List list = query.list();
			if (list != null && list.size() != 0) {
				SchoolHelp sh = (SchoolHelp) list.get(0);
				rb.setCode(200);
				if (status == 1) {// 提交
					if (uid != sh.getUid() && sh.getStatus() == 0&&sh.getPostUid()!=0) {
						sh.setPostUid(uid);
						sh.setStatus(1);
						session.save(sh);

					} else {
						rb.setCode(400);
						rb.setMessage("非法操作：请刷新确认您的操作");

					}
				} else if (status == 2) {// 确认
					if (uid == sh.getUid() && sh.getStatus() == 1) {
						sh.setStatus(2);
						session.save(sh);

					} else {
						rb.setCode(400);
						rb.setMessage("非法操作：请刷新确认您的操作");
					}
				} else if (status == 3) {// 驳回

					if (uid == sh.getUid() && sh.getStatus() == 1) {
						sh.setStatus(0);
						sh.setPostUid(0);
						session.save(sh);

					} else {
						rb.setCode(400);
						rb.setMessage("非法操作：请刷新确认您的操作");
					}
				} else {
					rb.setCode(400);
					rb.setMessage("非法操作：请刷新确认您的操作");
				}
			} else {
				rb.setCode(400);
				rb.setMessage("该条不存在");
			}
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			rb.setCode(400);
			rb.setMessage("服务器异常");
		}finally {
			session.close();
		}

		return rb;
	}

	@Override
	public ResultBean detail( int id) {
		// TODO Auto-generated method stub
		ResultBean rb=new ResultBean();
	
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		
		try{
			
			String hql="from SchoolHelp where id=?";
			Query query=session.createQuery(hql);
			
			query.setParameter(0, id);
			query.setMaxResults(1);
			List list=query.list();
			if(list!=null&&list.size()!=0){
				SchoolHelp sh=(SchoolHelp) list.get(0);
				CusSchoolHelp csh = new CusSchoolHelp();
				// 头像、名称
				String hql2 = "from UserInfo where id=?";
				query = session.createQuery(hql2);
				query.setParameter(0, sh.getUid());
				query.setMaxResults(1);

				List list1 = query.list();
				if (list1 != null && list1.size() != 0) {
					UserInfo ui = (UserInfo) list1.get(0);
					csh.setHeadPic(ui.getHeadPic());
					csh.setNickname(ui.getNickname());
				}
				csh.setSchoolHelp(sh);
				rb.setCode(200);
				rb.setData(csh);
			}else{
				rb.setCode(400);
				rb.setMessage("该条帮帮不存在");
			}
			
			ts.commit();
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ts.rollback();
			rb.setCode(400);
			rb.setMessage("服务器异常");
		}finally {
			session.close();
		}
		return rb;
	}

	public static class CusSchoolHelp extends SchoolHelp {

		private String nickname;
		private String headPic;

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

		public void setSchoolHelp(SchoolHelp sh) {
			setUid(sh.getUid());
			setTime(sh.getTime());
			setTitle(sh.getTitle());
			setContent(sh.getContent());
			setBounty(sh.getBounty());
			setStatus(sh.getStatus());
			setPicture(sh.getPicture());
			setPostUid(sh.getPostUid());
		}
	}

	
}
