package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.DAO.BlogDAO;
import com.niit.DAO.ForumDAO;
import com.niit.DAO.JobDAO;

import com.niit.DAOImpl.BlogDAOImpl;
import com.niit.DAOImpl.ForumDAOImpl;
import com.niit.DAOImpl.JobDAOImpl;

import com.niit.Model.Blog;
import com.niit.Model.Forum;
import com.niit.Model.Job;
import com.niit.Model.UserInfo;




@Configuration
@EnableTransactionManagement

@ComponentScan("com.niit")
public class DBConfig {

	@Bean(name = "dataSource")
	public DataSource getDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/xe");

		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");

		dataSource.setUsername("SYSTEM");
		dataSource.setPassword("password@123");

		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
	properties.put("hibernate.format_sql", "true");
	properties.put("hibernate.hbm2ddl.auto", "update");
		System.out.println("inn properties");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setAnnotatedClasses(Blog.class,Job.class,Forum.class,UserInfo.class);
		//sessionFactory.setPackagesToScan(new String[] { "com.niit.Model" });
		sessionFactory.setHibernateProperties(getHibernateProperties());
		System.out.println("session");
		return sessionFactory;
	}

	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}
	
	/*@Autowired
	@Bean(name = "userDAO")
	public UserDAO getusersDetailDao(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name = "jobDAO")
	public JobDAO getjobDao(SessionFactory sessionFactory) {
		return new JobDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name = "forumDAO")
	public ForumDAO getforumDao(SessionFactory sessionFactory) {
		return new ForumDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name = "blogDAO")
	public BlogDAO getBlogDao(SessionFactory sessionFactory) {
		return new BlogDAOImpl(sessionFactory);
	}
*/


}
