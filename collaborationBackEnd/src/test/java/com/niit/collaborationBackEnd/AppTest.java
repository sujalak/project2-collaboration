package com.niit.collaborationBackEnd;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.BlogDAO;
import com.niit.DAO.UserInfoDAO;
import com.niit.Model.Blog;
import com.niit.Model.UserInfo;

public class AppTest {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext();
		context.refresh();
		context.scan("com.niit.*");
		
	UserInfo	userInfo = (UserInfo) context.getBean("userInfo");

	UserInfoDAO userInfoDAO = (UserInfoDAO) context.getBean("userInfoDAO");
		
	}
 
}
