package com.xuyj.boatmate.model;



/**
 * Feedback entity. @author MyEclipse Persistence Tools
 */

public class Feedback  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private UserInfo userInfo;
     private String feedBack;
     private Long time;
     private Integer status;


    // Constructors

    /** default constructor */
    public Feedback() {
    }

    
    /** full constructor */
    public Feedback(UserInfo userInfo, String feedBack, Long time, Integer status) {
        this.userInfo = userInfo;
        this.feedBack = feedBack;
        this.time = time;
        this.status = status;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }
    
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getFeedBack() {
        return this.feedBack;
    }
    
    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public Long getTime() {
        return this.time;
    }
    
    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
   








}