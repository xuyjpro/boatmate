package com.xuyj.boatmate.action;

import com.xuyj.boatmate.dao.IFeedbackDAO;

public class PublishFeedback extends BaseAction {

	private String token;
	private String feedback;
	
	@Override
	public String post() {
		// TODO Auto-generated method stub
		IFeedbackDAO dao=(IFeedbackDAO) getApplicationContext().getBean("feedbackDAO");
		rb=dao.post(token, feedback);
		return SUCCESS;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

}
