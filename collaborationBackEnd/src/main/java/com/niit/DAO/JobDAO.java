package com.niit.DAO;

import java.util.List;

import com.niit.Model.Job;

public interface JobDAO {
	public boolean saveOrUpdateJob(Job job);
	

	public Job getJobById(int  jobId);

	public List<Job> getAllJobs();
	
	public boolean delete(int jobId);


}
