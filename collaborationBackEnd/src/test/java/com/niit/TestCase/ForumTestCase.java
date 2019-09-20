package com.niit.TestCase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.ForumDAO;
import com.niit.Model.Forum;

public class ForumTestCase {
	@Autowired
	private static Forum forum;
	@Autowired
	private static ForumDAO forumDAO;

	@BeforeClass
	public static void init() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		forum = (Forum) context.getBean("forum");

		forumDAO = (ForumDAO) context.getBean("forumDAO");

	}
	
	@Test
	public void saveOrUpdateForum() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		//forum.setForumId(111);
		forum.setName("jsp");
		forum.setContent("difference between start and run method");
		forum.setTopic("thread FAQ");
		forum.setUserName("rosy");
		forum.setCreatedDate(date);
		boolean flag = forumDAO.saveOrUpdateForum(forum);

		Assert.assertEquals("Failed to add the forum!", true, flag);
		
		
	}
	@Ignore
	@Test
	public void updateForumTestcase() {
		
		
		forum=forumDAO.getForumById(6);
		forum.setTopic("MultiThreading");
		forum.setName("core java");
		boolean flag = forumDAO.saveOrUpdateForum(forum);

		Assert.assertEquals("Failed to update the forum!", true, flag);

	}
	@Ignore
	@Test
public void deleteForumTestcase() {
			
		
		boolean flag = forumDAO.delete(7);

		Assert.assertEquals("Failed to delete the forum!", true, flag);

	}
	
	@Test
	public void getForumbyIdTestcase() {
		forum=forumDAO.getForumById(4);
		Assert.assertEquals("Failed to get the forum!", "jsp", forum.getName());
	}

	@Test
	public void getAllForumsTestCase() {
		int count=forumDAO.getAllForums().size();
		Assert.assertEquals("Failed to get the forums!", 1, count);
	}
	
	
	


}
