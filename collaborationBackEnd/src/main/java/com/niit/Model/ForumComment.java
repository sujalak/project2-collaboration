package com.niit.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
@Component
@Entity
public class ForumComment {
	@Id
	@GeneratedValue
	int commentId;
	String commentText;
	String userName;
	int forumId;
	Date commentDate;

}
