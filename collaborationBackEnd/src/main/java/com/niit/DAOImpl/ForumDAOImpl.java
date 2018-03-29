package com.niit.DAOImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.ForumDAO;
import com.niit.Model.Blog;
import com.niit.Model.BlogComment;
import com.niit.Model.Forum;
import com.niit.Model.ForumComment;


@Repository("forumDAO")
@Transactional

public class ForumDAOImpl implements ForumDAO {
	@Autowired
	private SessionFactory sessionFactory;


	public ForumDAOImpl(SessionFactory sessionFactory) {
	
		this.sessionFactory = sessionFactory;
	}


	public boolean saveOrUpdateForum(Forum forum) {
		Session session = sessionFactory.openSession();
		try {
			session.saveOrUpdate(forum);
			session.flush();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return false;
			
	}


	public Forum getForumById(int  forumId) {
		Session session = sessionFactory.getCurrentSession();
		
		return (Forum) session.get(Forum.class, forumId);
	}


	public List<Forum> getAllForums() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Forum ORDER BY forumId ASC");
		List<Forum> forums = query.list();
		return forums;

	}
	


	public boolean delete(int  forumId) {
		try {

			Forum forum = getForumById(forumId);
			sessionFactory.getCurrentSession().delete(forum);
			sessionFactory.getCurrentSession().flush();

			return true;
		} catch (HibernateException e) {
		
			e.printStackTrace();
		}
		return false;

	}


	public boolean approveForum(Forum forum) {
		try {
			forum.setStatus("A");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public boolean rejectForum(Forum forum) {
		try {
			forum.setStatus("NA");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public List<Forum> listForum(String username) {
	
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Forum where username=:username").setString("username",username);
		query.setParameter("username",username);
		
		List<Forum> forums = query.list();
		return forums;
	}


	public boolean addForumComment(ForumComment forumComment) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(forumComment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public boolean deleteForumComment(ForumComment forumComment) {
		try {
			sessionFactory.getCurrentSession().delete(forumComment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public ForumComment getForumComment(int commentId) {
		try {
			Session session = sessionFactory.openSession();
			ForumComment forumComment = (ForumComment)session.get(ForumComment.class,commentId);
			return forumComment;
		} catch (Exception e) {
			return null;
		}	
	}


	public List<ForumComment> listForumComments(int forumId) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from BlogComment where forumId=:forumId");
		query.setParameter("forumId", new Integer(forumId));
		@SuppressWarnings("unchecked")
		List<ForumComment> listForumComments=query.list();
		return listForumComments;
	}
	

	
}
