package com.niit.restcontroller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.BlogDAO;
import com.niit.Model.Blog;
import com.niit.Model.BlogComment;
@RestController
public class BlogCommentController {
	@Autowired
	BlogDAO blogDAO;

	
	// ---------------- Add comment-----------------------------------

	@PostMapping(value = "/postComment/{blogId}")
	public ResponseEntity<String> postComment(@RequestBody BlogComment blogComment,@PathVariable("blogId") int blogId) {
		 blogComment.setCommentDate(new Date());
		
			blogComment.setBlogId(blogId);
			//blogComment.setUserName($rootscope.username);
		
			
		if (blogDAO.addBlogComment(blogComment)) {
			return new ResponseEntity<String>("comment added - Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("comment insert failed", HttpStatus.NOT_FOUND);
		}
	}
}
