package com.xuyj.boatmate.dao;

import java.io.File;

import com.xuyj.boatmate.model.ResultBean;

public interface IUserInfoDAO {

	public ResultBean userLogin(String phone,String password);
	public ResultBean userRegister(String phone,String password);
	public ResultBean getUserInfo(String token);
	

	public ResultBean editUserInfo(String token,String nickname,int gender,String birthday,String heartWord,File headPic);
	public ResultBean modifyPasswd(String token,String oldPassword,String newPassword);

}
