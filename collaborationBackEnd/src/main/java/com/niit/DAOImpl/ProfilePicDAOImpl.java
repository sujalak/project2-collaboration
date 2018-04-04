package com.niit.DAOImpl;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.DAO.ProfilePicDAO;
import com.niit.Model.ProfilePic;
@Repository("profilePicDAO")
@Transactional
public class ProfilePicDAOImpl implements ProfilePicDAO{
	@Autowired
	SessionFactory sessionFactory;
	
	public ProfilePicDAOImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}

	public boolean insertOrUpdateProfilePicture(ProfilePic profilePic) {
		Session session = sessionFactory.openSession();
		try {
			
			session.saveOrUpdate(profilePic);
			session.flush();
			session.close();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

		
	}

	public ProfilePic getProfilePicture(String username) {
		Session session=sessionFactory.openSession();
        ProfilePic profilePicture=(ProfilePic)session.get(ProfilePic.class,username);
        session.close();
        return profilePicture;
	}

}
