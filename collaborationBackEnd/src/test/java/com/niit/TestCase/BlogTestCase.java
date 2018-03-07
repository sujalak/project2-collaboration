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

import com.niit.DAO.BlogDAO;
import com.niit.Model.Blog;

public class BlogTestCase {
	
	private static Blog blog;
	
	private static BlogDAO blogDAO;

	@BeforeClass
	public static void init() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		blog = (Blog) context.getBean("blog");

		blogDAO = (BlogDAO) context.getBean("blogDAO");

	}
	@Ignore
	@Test
	public void saveBlogTestcase() {
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		//LocalDate localDate = LocalDate.now();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		System.out.println(date);
		//blog.setBlogId(1114);
		blog.setBlogName("spring CORE");
		blog.setBlogContent(" useful for dependency injection.");
		blog.setCreatedDate(date);
		blog.setUserName("robort");
		boolean flag = blogDAO.saveOrUpdateBlog(blog);

		Assert.assertEquals("Failed to add the blog!", true, flag);

	}
	
	@Ignore
	@Test
	public void updateBlogTestcase() {
		
		
		blog=blogDAO.getBlogById(1);
		blog.setBlogName("springframework");
		
		boolean flag = blogDAO.saveOrUpdateBlog(blog);

		Assert.assertEquals("Failed to update the blog!", true, flag);

	}
	@Ignore
	@Test
public void deleteBlogTestcase() {
			
		
		boolean flag = blogDAO.delete(2);

		Assert.assertEquals("Failed to delete the blog!", true, flag);

	}
	
	@Test
	public void getBlogbyIdTestcase() {
		blog=blogDAO.getBlogById(1);
		Assert.assertEquals("Failed to get the blog!", "springframework", blog.getBlogName());
	}
	
	@Test
	public void getAllBlogsTestCase() {
		int count=blogDAO.getAllBlogs().size();
		Assert.assertEquals("Failed to get the blogs!", 3, count);
	}
	
	
}
