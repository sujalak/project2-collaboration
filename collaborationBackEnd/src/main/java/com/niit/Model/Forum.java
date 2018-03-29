package com.niit.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Forum {
	@Id
	  @GeneratedValue
    private int forumId;
	private String name;
	private String topic;
	private String content;
	 private Date createdDate;
	 private String userName;
	 private String status;
	 
	 @OneToMany(targetEntity = ForumComment.class, fetch = FetchType.EAGER, mappedBy = "forum",cascade = CascadeType.ALL)
		private Set<ForumComment> forumComments = new HashSet<ForumComment>(0);

	public Set<ForumComment> getForumComments() {
		return forumComments;
	}
	public void setForumComments(Set<ForumComment> forumComments) {
		this.forumComments = forumComments;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
