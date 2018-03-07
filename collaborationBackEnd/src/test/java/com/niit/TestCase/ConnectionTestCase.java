package com.niit.TestCase;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.Model.Blog;



	
	public class ConnectionTestCase{
	
		@Test
		public void connectiontest()
		{
			AnnotationConfigApplicationContext  context = new AnnotationConfigApplicationContext();
			context.scan("com.niit"); 
			context.refresh();
			
	     
		}

		
	}
	

