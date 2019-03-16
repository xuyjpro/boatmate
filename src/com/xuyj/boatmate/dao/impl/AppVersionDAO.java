package com.xuyj.boatmate.dao.impl;



import java.io.File;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xuyj.boatmate.dao.BaseDAO;
import com.xuyj.boatmate.model.AppVersion;
import com.xuyj.boatmate.model.ResultBean;
import com.xuyj.boatmate.tools.ImageTools;

public class AppVersionDAO extends BaseDAO {

	public ResultBean getAppVersion(){
		ResultBean rb=new ResultBean();
		
		Session session=(Session) getSession();
		Transaction ts=session.beginTransaction();
		
		try{
			String hql="from AppVersion order by id desc";
			Query query=session.createQuery(hql);
			
			query.setMaxResults(1);
			List list=query.list();
			rb.setCode(200);
			
			rb.setData(list);
			ts.commit();
			
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
	public ResultBean publishVersion(int versionCode,String versionName,File apkFile){
		ResultBean rb=new ResultBean();
		
		
		if(apkFile==null||versionCode==0||versionName==null||versionName.isEmpty()){
			
			rb.setCode(400);
			rb.setMessage("参数不全");
			return rb;
		}
		Session session=(Session) getSession();
		Transaction ts=session.beginTransaction();
		try{
			AppVersion av=new AppVersion();
			av.setVersionCode(versionCode);
			av.setVersionName(versionName);
			av.setApkName("toget-"+versionName+".apk");
			
			ImageTools.saveImage(apkFile,"toget-"+versionName+".apk");
			av.setDownloadUrl(av.getApkName());
			session.save(av);
			ts.commit();
			
			rb.setCode(200);
			rb.setData(av);
		}catch(Exception e){
			
			
			ts.rollback();
			rb.setCode(400);
			rb.setData(e.getMessage());
			
		}finally {
			session.close();
		}
		
		return rb;
		
		
	}
}
