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
	
	private static UserInfo userInfo;
	
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
		userInfo.setUserName("sujala");
		userInfo.setEmailId("sujala@gmail.com");
		userInfo.setPassword("12345");
		userInfo.setConatctNo("888765432");
		userInfo.setIsOnline("Y");
		userInfo.setRole("ROLE_ADMIN");
		boolean flag = userInfoDAO.saveOrUpdateUserInfo(userInfo);

		Assert.assertEquals("Failed to add the userInfo!", true, flag);
	}
	

	//@Ignore
	@Test
	public void updateUserInfoTestcase() {

		userInfo = userInfoDAO.getUserInfoByName("neeta");
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


	@Ignore
	@Test
	public void getUserInfobyNameTestcase() {
		userInfo = userInfoDAO.getUserInfoByName("aju");
		Assert.assertEquals("Failed to get the userInfo!", "aju@123.com", userInfo.getEmailId());
	}
	@Ignore
	@Test
	public void getAllUserInfosTestCase() {
		int count = userInfoDAO.getAllUserInfo().size();
		Assert.assertEquals("Failed to get the userInfos!", 2, count);
	}

}



