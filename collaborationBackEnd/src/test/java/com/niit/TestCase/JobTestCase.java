package com.niit.TestCase;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.JobDAO;
import com.niit.Model.Job;

public class JobTestCase {

	private static Job job;

	private static JobDAO jobDAO;

	@BeforeClass
	public static void init() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		job = (Job) context.getBean("job");

		jobDAO = (JobDAO) context.getBean("jobDAO");

	}
	@Ignore

	@Test
	public void saveJobTestcase() {
		//job.setJobid(1284);
		job.setDesignation("tech mentor");
		job.setJobdescription("java trainer");
		job.setSalary(35000);
		boolean flag = jobDAO.saveOrUpdateJob(job);

		Assert.assertEquals("Failed to add the job!", true, flag);
	}
	@Ignore

	@Test
	public void updateJobTestcase() {

		job = jobDAO.getJobById(9);
		job.setDesignation("branch manager");
         job.setSalary(70000);
		boolean flag = jobDAO.saveOrUpdateJob(job);

		Assert.assertEquals("Failed to update the job!", true, flag);

	}

	
	@Test
	public void deleteJobTestcase() {

		boolean flag = jobDAO.delete(8);

		Assert.assertEquals("Failed to delete the job!", true, flag);

	}


	@Test
	public void getJobbyIdTestcase() {
		job = jobDAO.getJobById(9);
		Assert.assertEquals("Failed to get the job!", "tech mentor", job.getDesignation());
	}

	@Test
	public void getAllJobsTestCase() {
		int count = jobDAO.getAllJobs().size();
		Assert.assertEquals("Failed to get the jobs!", 1, count);
	}

}
