package com.xuyj.boatmate.model;
public class UserInfoWithToken{
		private UserInfo userInfo;
		private String token;
		public UserInfo getUserInfo() {
			return userInfo;
		}
		public void setUserInfo(UserInfo userInfo) {
			this.userInfo = userInfo;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		
}