package com.niit.DAOImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.UserInfoDAO;
import com.niit.Model.UserInfo;
import com.niit.Model.UserInfo;

@Repository("userInfoDAO")
@Transactional
public class UserInfoDAOImpl implements UserInfoDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public UserInfoDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public boolean saveOrUpdateUserInfo(UserInfo userInfo) {
		Session session = sessionFactory.openSession();
		try {
			userInfo.setIsOnline("N");
			userInfo.setRole("ROLE_USER");
			session.saveOrUpdate(userInfo);
			session.flush();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public UserInfo getUserInfoByName(String userName) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from UserInfo where userName = '" + userName + "'");
		System.out.println(query.list());
		UserInfo userInfo = (UserInfo) query.list().get(0);
		return userInfo;
	}

	public List<UserInfo> getAllUserInfo() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from UserInfo ORDER BY userName ASC");
		List<UserInfo> userInfos = query.list();
		return userInfos;

	}

	public boolean delete(String userName) {
		try {

			UserInfo userInfo = getUserInfoByName(userName);
			sessionFactory.getCurrentSession().delete(userInfo);
			sessionFactory.getCurrentSession().flush();

			return true;
		} catch (HibernateException e) {

			e.printStackTrace();
		}
		return false;

	}

	public boolean validateLogin(UserInfo userInfo) {

		try {
			Session session = sessionFactory.openSession();
			Query query=session.createQuery("from UserInfo where userName=:userName and password=:password");
		
			query.setParameter("userName", userInfo.getUserName());
			query.setParameter("password", userInfo.getPassword());
			UserInfo userInfonew=(UserInfo)query.list().get(0);
			
			session.close();
			if (userInfonew == null)
				return false;
			else
				return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateOnlineStatus(String status, String userName) {
		try {
			UserInfo userInfo = getUserInfoByName(userName);
			userInfo.setIsOnline(status);
			sessionFactory.getCurrentSession().delete(userInfo);
			sessionFactory.getCurrentSession().flush();

			return true;

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
