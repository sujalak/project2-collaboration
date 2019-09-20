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

import com.niit.DAO.UserInfoDAO;
import com.niit.Model.UserInfo;
@RestController
public class UserController {
	@Autowired
	UserInfoDAO userInfoDAO;

	//----------------validate login------------------
	

	@PostMapping(value = "SignUp")
	public ResponseEntity<String> saveUserInfo(@RequestBody UserInfo userInfo) {
		userInfo.setRole("ROLE_USER");
		if (userInfoDAO.saveOrUpdateUserInfo(userInfo)) {
			return new ResponseEntity<String>("UserInfo Added- Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("UserInfo insert failed", HttpStatus.NOT_FOUND);
		}
	}

	// ---------------- Add UserInfo -----------------------------------

	@PostMapping(value = "login")
	public ResponseEntity<UserInfo> validateLogin(@RequestBody UserInfo userInfo) {
		
		if (userInfoDAO.validateLogin(userInfo)) {
			UserInfo tempUser=(UserInfo)userInfoDAO.getUserInfoByName(userInfo.getUserName());
			userInfoDAO.updateOnlineStatus("Y",userInfo.getUserName());
			return new ResponseEntity<UserInfo>(userInfo, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserInfo>(userInfo, HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
}
