package com.niit.DAO;

import java.util.List;

import com.niit.Model.UserInfo;

public interface UserInfoDAO {
	public boolean saveOrUpdateUserInfo(UserInfo userInfo);

	public UserInfo getUserInfoByName(String userName);

	public List<UserInfo> getAllUserInfo();

	public boolean delete(String userName);
	public boolean validateLogin(UserInfo userInfo);
	public boolean updateOnlineStatus(String status, String userName);

}
