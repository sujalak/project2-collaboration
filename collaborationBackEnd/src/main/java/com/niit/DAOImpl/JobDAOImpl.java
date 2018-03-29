package com.niit.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.JobDAO;
import com.niit.Model.ApplyJob;
import com.niit.Model.Job;

@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO{
	@Autowired
	private SessionFactory sessionFactory;
	

	
	public JobDAOImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}

	public boolean saveOrUpdateJob(Job job) {
		Session session = sessionFactory.openSession();
		try {
			session.saveOrUpdate(job);
			session.flush();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return false;
			
	}

	public Job getJobById(int jobId) {
		Session session = sessionFactory.openSession();
		return (Job) session.get(Job.class, jobId);

	}

	public List<Job> getAllJobs() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Job ORDER BY jobId ASC");
		List<Job> jobs = query.list();
		return jobs;

	}

	public boolean delete(int jobId) {
		try {

			Job job = getJobById(jobId);
			sessionFactory.getCurrentSession().delete(job);
			sessionFactory.getCurrentSession().flush();

			return true;
		} catch (HibernateException e) {
		
			e.printStackTrace();
		}
		return false;
	}

	public boolean applyJob(ApplyJob applyJob) {
		try {
			sessionFactory.getCurrentSession().save(applyJob);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public List<ApplyJob> getAllAppliedJobDetails() {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			List<ApplyJob> appliedjobList = new ArrayList<ApplyJob>();
			Query query = session.createQuery("FROM ApplyJob");
			appliedjobList = query.list();
			return appliedjobList;
		} catch (Exception e) {
			return null;
		}
	}
	

}
