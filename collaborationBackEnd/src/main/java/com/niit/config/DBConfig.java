package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.DAO.BlogDAO;
import com.niit.DAO.ForumDAO;
import com.niit.DAO.JobDAO;
import com.niit.DAO.UserInfoDAO;
import com.niit.DAOImpl.BlogDAOImpl;
import com.niit.DAOImpl.JobDAOImpl;
import com.niit.DAOImpl.UserInfoDAOImpl;
import com.niit.Model.ApplyJob;
import com.niit.Model.Blog;
import com.niit.Model.BlogComment;
import com.niit.Model.Forum;
import com.niit.Model.ForumComment;
import com.niit.Model.Job;
import com.niit.Model.ProfilePic;
import com.niit.Model.UserInfo;




@Configuration
@EnableTransactionManagement

@ComponentScan("com.niit.*")
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
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		
	
		
		LocalSessionFactoryBuilder sessionFactoryBuilder=new LocalSessionFactoryBuilder(getDataSource());
		sessionFactoryBuilder.addProperties(getHibernateProperties());
		
		sessionFactoryBuilder.addAnnotatedClass(Blog.class);
		sessionFactoryBuilder.addAnnotatedClass(BlogComment.class);
		sessionFactoryBuilder.addAnnotatedClass(Forum.class);
		sessionFactoryBuilder.addAnnotatedClass(ForumComment.class);
		sessionFactoryBuilder.addAnnotatedClass(Job.class);
		sessionFactoryBuilder.addAnnotatedClass(ApplyJob.class);
		sessionFactoryBuilder.addAnnotatedClass(UserInfo.class);
		sessionFactoryBuilder.addAnnotatedClass(ProfilePic.class);
		SessionFactory sessionFactory=sessionFactoryBuilder.buildSessionFactory();
		System.out.println("----SessionFactory Object----------");
		return sessionFactory;
	}


	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}
	/*@Autowired
	@Bean(name="blogDAO")
	public BlogDAO getBlogDAO()
	{
		return new BlogDAOImpl();
	}*/

	


}
