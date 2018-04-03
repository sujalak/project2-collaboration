package com.niit.DAO;

import com.niit.Model.ProfilePic;

public interface ProfilePicDAO {
	public boolean insertOrUpdateProfilePicture(ProfilePic profilePic);
	public ProfilePic getProfilePicture(String username);
}
