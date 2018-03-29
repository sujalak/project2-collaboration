package com.niit.DAO;

import java.util.List;

import com.niit.Model.Forum;
import com.niit.Model.ForumComment;
import com.niit.Model.Forum;

public interface ForumDAO {
	
		public boolean saveOrUpdateForum(Forum forum);
		

		public Forum getForumById(int  forumId);

		public List<Forum> getAllForums();
		
		public boolean delete(int forumId);


		public boolean approveForum(Forum forum);

		public boolean rejectForum(Forum forum);

		public List<Forum> listForum(String username);

		
		public boolean addForumComment(ForumComment forumComment);

		public boolean deleteForumComment(ForumComment forumComment);

		public ForumComment getForumComment(int commentId);

		public List<ForumComment> listForumComments(int forumId);
	

}
