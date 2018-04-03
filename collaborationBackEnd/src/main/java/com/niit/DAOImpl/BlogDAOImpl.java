package com.niit.DAOImpl;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.BlogDAO;
import com.niit.Model.Blog;
import com.niit.Model.BlogComment;


@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {
	public BlogDAOImpl() {
		
	}

	@Autowired(required=true)
	private SessionFactory sessionFactory;

	public BlogDAOImpl(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	public boolean saveOrUpdateBlog(Blog blog) {
		Session session = sessionFactory.openSession();
		try {
			session.saveOrUpdate(blog);
			session.flush();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return false;

	}
	
	
	 

	public Blog getBlogById(int blogId) {
		try
		{
		Session session = sessionFactory.getCurrentSession();
		return (Blog) session.get(Blog.class, blogId);
		} catch (Exception e) {
			return null;
		}	

	}

	public boolean delete(int blogId) {
		try {

			Blog blog = getBlogById(blogId);
			sessionFactory.getCurrentSession().delete(blog);
			sessionFactory.getCurrentSession().flush();

			return true;
		} catch (HibernateException e) {

			e.printStackTrace();
		}
		return false;

	}

	public List<Blog> getAllBlogs() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Blog ORDER BY blogId ASC");
		List<Blog> blogs = query.list();
		return blogs;

	}

	public boolean approveBlog(Blog blog) {
		try {
			blog.setStatus("A");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean rejectBlog(Blog blog) {
		try {
			blog.setStatus("NA");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*public List<Blog> listBlog(String username) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Blog where username=:username").setString("username",username);
		query.setParameter("username",username);
		
		List<Blog> blogs = query.list();
		return blogs;

	}*/

	public List<Blog> listBlogs() {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Blog");
		
		
		List<Blog> blogs = query.list();
		return blogs;

	}
	public boolean incrementLike(Blog blog) {
		try {
			int likes = blog.getLikes();
			likes++;
			blog.setLikes(likes);
			sessionFactory.getCurrentSession().update(blog);

			return true;
		} catch (HibernateException e) {

			e.printStackTrace();
		}
		return false;

	}

	public boolean addBlogComment(BlogComment blogComment) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(blogComment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteBlogComment(BlogComment blogComment) {
		try {
			sessionFactory.getCurrentSession().delete(blogComment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public BlogComment getBlogComment(int commentId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			BlogComment blogComment = (BlogComment) session.get(BlogComment.class,commentId);
			return blogComment;
		} catch (Exception e) {
			return null;
		}	
	}

	public List<BlogComment> listBlogComments(int blogId) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogComment where blogId=:blogId");
		query.setParameter("blogId", new Integer(blogId));
		@SuppressWarnings("unchecked")
		List<BlogComment> listBlogComments=query.list();
		return listBlogComments;
	}

}
