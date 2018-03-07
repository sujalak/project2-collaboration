package com.niit.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
@Component
@Entity
public class UserInfo {
	@Id
	String userName;
	String password;
	String emailId;
	String conatctNo;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getConatctNo() {
		return conatctNo;
	}
	public void setConatctNo(String conatctNo) {
		this.conatctNo = conatctNo;
	}
	

}
