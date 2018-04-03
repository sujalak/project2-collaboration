package com.niit.TestCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.BlogDAO;
import com.niit.Model.Blog;
import com.niit.Model.BlogComment;

public class BlogTestCase {
	@Autowired
	private static Blog blog;

	private static BlogDAO blogDAO;
	@Autowired
	private BlogComment blogComment;

	@BeforeClass
	public static void init() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		blog = (Blog) context.getBean("blog");

		blogDAO = (BlogDAO) context.getBean("blogDAO");

	}
	//@Ignore
	@Test
	public void saveBlogTestcase() {
		
	
		Date date = new Date();
	
		System.out.println(date);
		// blog.setBlogId(1114);
		blog.setBlogName("Bootstrap");
		blog.setBlogContent("Bootstrap is the most popular HTML, CSS, and JavaScript framework for developing responsive, mobile-first websites.");
		blog.setCreatedDate(date);
		blog.setUserName("sonu");
		blog.setStatus("A");
		blog.setLikes(0);
		//blog.setBlogComments(null);
		boolean flag = blogDAO.saveOrUpdateBlog(blog);

		//Assert.assertEquals("Failed to add the blog!", true, flag);

	}

	@Ignore
	@Test
	public void listBlogTestcase() {

		int count = blogDAO.listBlogs().size();

		Assert.assertEquals("Failed to list the blog!", 2, count);

	}
	@Ignore
	@Test
	public void updateBlogTestcase() {

		blog = blogDAO.getBlogById(27);
		//blog.setBlogName("HTML");
blog.setStatus("NA");
		boolean flag = blogDAO.saveOrUpdateBlog(blog);

		Assert.assertEquals("Failed to update the blog!", true, flag);

	}

	@Ignore
	@Test
	public void deleteBlogTestcase() {

		boolean flag = blogDAO.delete(29);

		Assert.assertEquals("Failed to delete the blog!", true, flag);

	}

	@Ignore
	@Test
	public void getBlogbyIdTestcase() {
		blog = blogDAO.getBlogById(28);
		Assert.assertEquals("Failed to get the blog!", "springframework", blog.getBlogName());
	}

	@Ignore
	@Test
	public void getAllBlogsTestCase() {
		int count = blogDAO.getAllBlogs().size();
		Assert.assertEquals("Failed to get the blogs!", 3, count);
	}
@Ignore
	@Test
	public void ApproveBlogTestcase() {
		blog = blogDAO.getBlogById(1);
		String sts = blog.getStatus();
		if (sts.equals("NA")) {
			assertEquals("Successfully approved blog int the table", true, blogDAO.approveBlog(blog));
			System.out.println("<-----------Successfully approved blog-------->");
		} else {
			System.out.println("not approved");
		}
	}

	@Ignore
	@Test
	public void RejectBlogTestcase() {
		blog = blogDAO.getBlogById(27);
		String sts = blog.getStatus();
		if (sts.equals("A")) {
			assertEquals("Successfully approved blog int the table", true, blogDAO.rejectBlog(blog));
			System.out.println("<-----------Successfully rejected blog-------->");
		} else {
			System.out.println("not approved");
		}
	}
	//@Ignore
	@Test
	public void IncrementLikesTestcase() {
		blog = blogDAO.getBlogById(1);
		assertEquals("Successfully incremented likes to the table", true, blogDAO.incrementLike(blog));
		System.out.println("<=========Likes=========>");
		System.out.println("Likes After incrementing :" + blog.getLikes());
		System.out.println("<-----------Successfully incremented blog likes-------->");
	}

	//@Ignore
	@Test
	public void AddBlogCommentTestcase() {
		blogComment = new BlogComment();
		blog = blogDAO.getBlogById(1);
		String username = blog.getUserName();
		int blogId = blog.getBlogId();
		blogComment.setBlogId(blogId);
		blogComment.setUserName(username);
		blogComment.setCommentDate(new Date());
		blogComment.setCommentText("HTML blog");
		assertEquals("Successfully added the blogComment...", true, blogDAO.addBlogComment(blogComment));
		System.out.println("<-----------Successfully added blogCommment-------->");
	}

	@Ignore
	@Test
	public void GetBlogCommmentTestcase() {
		blogComment = blogDAO.getBlogComment(3);
		assertEquals("Successfully fetched a blogComments from the table", "sonu", blogComment.getUserName());
		System.out.println("<========BlogComment========>");
		System.out.println("blogID :" + blogComment.getBlogId());
		System.out.println("Username :" + blogComment.getUserName());
		System.out.println("Status :" + blogComment.getCommentId());
		System.out.println("Likes :" + blogComment.getCommentText());
		System.out.println("Created Date :" + blogComment.getCommentDate());
		System.out.println("<-----------Successfully fetched blogComment-------->");
	}

	@Ignore
	@Test
	public void DeleteBlogCommentTestcase() {
		blogComment = blogDAO.getBlogComment(3);
		assertEquals("Successfully deleted blog details from the table", true, blogDAO.deleteBlogComment(blogComment));
		System.out.println("--------Successfully deleted blogComment----");
	}

	 @Ignore
	@Test
	public void ListBlogCommentsTestcase() {
		List<BlogComment> listBlogComments = blogDAO.listBlogComments(3);
		assertTrue("Successfully fetched all blogs from the table", blogDAO.listBlogComments(1).size() > 0);
		System.out.println("<======BlogComments fetched======>");
		for (BlogComment blogComment : listBlogComments) {
			System.out.println("blogID :" + blogComment.getBlogId());
			System.out.println("CommentID :" + blogComment.getCommentId());
			System.out.println("Comment Text :" + blogComment.getCommentText());
			System.out.println("Username :" + blogComment.getUserName());
			System.out.println("Comment Date : " + blogComment.getCommentDate());
		}
		System.out.println("Successfully retrieved list of blogComments");
	}


}
