package com.niit.TestCase;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.UserInfoDAO;
import com.niit.Model.UserInfo;

public class UserInfoTestCase {
	@Autowired
	private static UserInfo userInfo;
	@Autowired
	private static UserInfoDAO userInfoDAO;

	@BeforeClass
	public static void init() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		userInfo = (UserInfo) context.getBean("userInfo");

		userInfoDAO = (UserInfoDAO) context.getBean("userInfoDAO");

	}

	@Test
	public void saveUserInfoTestcase() {
		//userInfo.setUserInfoid(1284);
		userInfo.setUserName("rupa");
		userInfo.setEmailId("rupa@gmail.com");
		userInfo.setPassword("12345");
		userInfo.setConatctNo("98765329");
		boolean flag = userInfoDAO.saveOrUpdateUserInfo(userInfo);

		Assert.assertEquals("Failed to add the userInfo!", true, flag);
	}
	

	@Ignore
	@Test
	public void updateUserInfoTestcase() {

		userInfo = userInfoDAO.getUserInfoByName("anveet");
		userInfo.setPassword("1234abc");
         
		boolean flag = userInfoDAO.saveOrUpdateUserInfo(userInfo);

		Assert.assertEquals("Failed to update the userInfo!", true, flag);

	}


	@Ignore
	@Test
	public void deleteUserInfoTestcase() {

		boolean flag = userInfoDAO.delete("anveet");

		Assert.assertEquals("Failed to delete the userInfo!", true, flag);

	}



	@Test
	public void getUserInfobyNameTestcase() {
		userInfo = userInfoDAO.getUserInfoByName("aju");
		Assert.assertEquals("Failed to get the userInfo!", "aju@123.com", userInfo.getEmailId());
	}
	
	@Test
	public void getAllUserInfosTestCase() {
		int count = userInfoDAO.getAllUserInfo().size();
		Assert.assertEquals("Failed to get the userInfos!", 2, count);
	}

}



