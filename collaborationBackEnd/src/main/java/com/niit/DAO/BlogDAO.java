package com.niit.DAO;

import java.util.List;

import com.niit.Model.Blog;
import com.niit.Model.BlogComment;

public interface BlogDAO {
	public boolean saveOrUpdateBlog(Blog blog);

	public Blog getBlogById(int blogId);

	public List<Blog> getAllBlogs();

	public boolean delete(int blogId);

	public boolean approveBlog(Blog blog);

	public boolean rejectBlog(Blog blog);

	public List<Blog> listBlogs();

	public boolean incrementLike(Blog blog);

	public boolean addBlogComment(BlogComment blogComment);

	public boolean deleteBlogComment(BlogComment blogComment);

	public BlogComment getBlogComment(int commentId);

	public List<BlogComment> listBlogComments(int blogid);
}
