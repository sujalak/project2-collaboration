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
import com.niit.Model.Forum;


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
		
		return session.get(Forum.class, forumId);
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

	
}
