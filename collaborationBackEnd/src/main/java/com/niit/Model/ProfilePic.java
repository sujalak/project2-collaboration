package com.niit.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class ProfilePic {
	
	
		@Id
		private String username;
		@Lob
		private byte[] image;
		public String getUsername() {
			return username;
		}	
		public void setUsername(String username) {
			this.username = username;
		}
		public byte[] getImage() {
			return image;
		}
		public void setImage(byte[] image) {
			this.image = image;
		}
		
	}


