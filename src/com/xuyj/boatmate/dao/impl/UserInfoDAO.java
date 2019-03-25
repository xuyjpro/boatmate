package com.xuyj.boatmate.dao.impl;

import java.io.File;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xuyj.boatmate.dao.BaseDAO;
import com.xuyj.boatmate.dao.IUserInfoDAO;
import com.xuyj.boatmate.model.ResultBean;
import com.xuyj.boatmate.model.UserInfo;
import com.xuyj.boatmate.model.UserInfoWithToken;
import com.xuyj.boatmate.tools.ImageTools;
import com.xuyj.boatmate.tools.SecretUtils;
import com.xuyj.boatmate.tools.ToolUtil;

public class UserInfoDAO extends BaseDAO implements IUserInfoDAO {

	@Override
	public ResultBean userLogin(String phone, String password) {
		// TODO Auto-generated method stub
		ResultBean rb = new ResultBean();

		if (phone == null || phone.isEmpty() || password == null || password.isEmpty()) {
			rb.setCode(400);
			rb.setMessage("参数不完整");
			return rb;

		}

		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from UserInfo u where u.phone=?";

			Query query = session.createQuery(hql);
			query.setParameter(0, phone);
			query.setMaxResults(1);
			List users = query.list();
			if (users == null || users.size() == 0) {// 没有则未注册
				rb.setCode(400);
				rb.setMessage("用户名不存在");
			} else {
				UserInfo user = (UserInfo) users.get(0);
				if (!user.getPassword().equals(password)) {
					rb.setCode(400);
					rb.setMessage("密码不正确");
				} else {
					UserInfoWithToken uiwt = new UserInfoWithToken();

					uiwt.setUserInfo(user);
					uiwt.setToken(SecretUtils.encode(user.getId() + ""));
					rb.setCode(200);
					rb.setMessage("success");
					rb.setData(uiwt);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			if (ts != null) {
				ts.rollback();
			}
			rb.setCode(400);
			rb.setMessage(e.getMessage());
		} finally {
			session.close();

		}
		return rb;
	}

	@Override
	public ResultBean userRegister(String phone, String password) {
		// TODO Auto-generated method stub

		ResultBean rb = new ResultBean();
		if (phone == null || phone.isEmpty() || password == null || password.isEmpty()) {
			rb.setCode(400);
			rb.setMessage("参数不完整");
			return rb;

		}
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "from UserInfo u where u.phone=?";

			Query query = session.createQuery(hql);
			query.setParameter(0, phone);
			query.setMaxResults(1);
			List users = query.list();
			if (users == null || users.size() == 0) {// 没有则未注册
				UserInfo userInfo = new UserInfo();
				userInfo.setPhone(phone);
				userInfo.setPassword(password);
				userInfo.setGender(true);
				userInfo.setBirthday("1997-11-20");
				userInfo.setHeartWord("快来发布心情吧~");
				session.save(userInfo);

				// 查询ID

				query = session.createQuery("from UserInfo u where u.phone=?");
				query.setParameter(0, phone);
				query.setMaxResults(1);

				users.clear();
				users = query.list();
				UserInfo ui = (UserInfo) users.get(0);

				UserInfoWithToken uiwt = new UserInfoWithToken();
				uiwt.setUserInfo(ui);
				uiwt.setToken(SecretUtils.encode(ui.getId() + ""));

				rb.setCode(200);
				rb.setMessage("success");
				rb.setData(uiwt);
			} else {
				rb.setCode(400);
				rb.setMessage("该用户名已存在");
			}
			ts.commit();
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
	public ResultBean getUserInfo(String token) {
		// TODO Auto-generated method stub
		ResultBean rb = new ResultBean();
		if (token == null) {
			rb.setCode(400);
			rb.setMessage("参数不完整");
		} else {

			int uid;
			try {
				uid = Integer.parseInt(SecretUtils.decode(token));

			} catch (NumberFormatException e) {

				rb.setCode(400);
				rb.setMessage("该用户不存在");
				return rb;
			}
			Session session = getSession();
			Transaction ts = session.beginTransaction();

			try {
				Query query = session.createQuery("from UserInfo where id=?");
				query.setParameter(0, uid);

				query.setMaxResults(1);
				List users = query.list();
				if (users == null || users.size() == 0) {
					rb.setCode(400);
					rb.setMessage("该用户不存在");
					return rb;
				}
				
				ts.commit();

				UserInfo user = (UserInfo) users.get(0);
				rb.setCode(200);
				rb.setMessage("success");
				user.setPassword("");
				rb.setData(user);

			} catch (Exception e) {
				// TODO: handle exception
				ts.rollback();
				rb.setCode(400);
				rb.setMessage(e.getMessage());
			} finally {
				session.close();
			}

		}
		return rb;
	}

	@Override
	public ResultBean editUserInfo(String token, String nickname, int gender, String birthday, String heartWord,
			File headPic) {
		// TODO Auto-generated method stub
		ResultBean rb = new ResultBean();
		int uid;
		try {
			uid = Integer.parseInt(SecretUtils.decode(token));
		} catch (Exception e) {
			// TODO: handle exception
			rb.setCode(400);
			rb.setMessage("请传入正确身份验证信息");
			return rb;
		}
		long time = 0;

		if (headPic != null) { // 先判断是否有图片
			time = System.currentTimeMillis();
			//String fileName = ToolUtil.getWebRootSubDir("/image") + File.separator + time;
			try {
				if (!ImageTools.saveImage(headPic, time+"")) {// 保存失败则直接返回
					rb.setCode(400);
					rb.setMessage("图片上传失败");
					return rb;
				}
			} catch (Exception e) {
				// TODO: handle exception
				rb.setCode(400);
				rb.setMessage("图片上传失败");
				return rb;
			}
		}
		Session session = getSession();
		Transaction ts = session.beginTransaction();

		try {

			Query query = session.createQuery("from UserInfo where id=?");
			query.setParameter(0, uid);
			query.setMaxResults(1);
			List users = query.list();

			if (users == null || users.size() == 0) {
				rb.setCode(400);
				rb.setMessage("身份信息有误");

			} else {
				UserInfo user = (UserInfo) users.get(0);

				if (nickname != null) {
					user.setNickname(nickname);
				}
				if (gender != -1) {
					user.setGender(gender == 1 ? true : false);
				}
				if (birthday != null) {
					user.setBirthday(birthday);
				}
				if (heartWord != null) {
					user.setHeartWord(heartWord);
				}
				if (time != 0) {
					user.setHeadPic(time + "");
				}

				session.update(user);

				rb.setCode(200);
				rb.setMessage("success");
				rb.setData(user);
			}
			ts.commit();

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

		return rb;
	}

	@Override
	public ResultBean modifyPasswd(String token, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		ResultBean rb = new ResultBean();
		int uid;
		try {
			uid = Integer.parseInt(SecretUtils.decode(token));
		} catch (Exception e) {
			// TODO: handle exception
			rb.setCode(400);
			rb.setMessage("请传入正确身份验证信息");
			return rb;
		}
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try{
			String hql="from UserInfo where id=?";
			Query query=session.createQuery(hql);
			query.setParameter(0, uid);
			
			List list=query.list();
			if(list!=null&&list.size()!=0){
				UserInfo userInfo =(UserInfo) list.get(0);
				if(userInfo.getPassword().equals(oldPassword)){
					userInfo.setPassword(newPassword);
					session.update(userInfo);
					ts.commit();
					rb.setCode(200);
				}else{
					rb.setCode(400);
					rb.setMessage("旧密码不正确");
				}
				
			}else{
				rb.setCode(400);
				rb.setMessage("身份信息错误，请重新登录");
			}
			
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
