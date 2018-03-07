package com.niit.DAO;

import java.util.List;

import com.niit.Model.Blog;



public interface BlogDAO {
	public boolean saveOrUpdateBlog(Blog blog);
	

	public Blog getBlogById(int blogId);

	public List<Blog> getAllBlogs();
	
	public boolean delete(int blogId);


}
