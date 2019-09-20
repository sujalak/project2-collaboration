package com.niit.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.DAO.ProfilePicDAO;
import com.niit.Model.ProfilePic;
import com.niit.Model.UserInfo;
@RestController
public class ProfilePicController {
	@Autowired
	ProfilePicDAO profilePicDAO;
	
	@RequestMapping(value="/doUpload",method=RequestMethod.POST)
	public ResponseEntity<?> uploadPicture(@RequestParam(value="file")CommonsMultipartFile fileupload,HttpSession session)
	{
	System.out.println( "rest controller upload");
		UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
		
		if(userInfo==null) 
		{
			System.out.println( "useri");
			
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		else
		{
			ProfilePic profilePicture=new ProfilePic();
			//profilePicture.setUsername(userInfo.getUserName());
			profilePicture.setImage(fileupload.getBytes());
			profilePicDAO.insertOrUpdateProfilePicture(profilePicture);
			System.out.println( "user uploaded");
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getImage/{username}")
	public @ResponseBody byte[] getProfilePic(@PathVariable("userInfo") String username)
	{
		
		ProfilePic profilePicture=profilePicDAO.getProfilePicture(username);
		
		if(profilePicture==null)
		{
			return null;
		}
		else
		{
			return profilePicture.getImage();
		}
	}

}
