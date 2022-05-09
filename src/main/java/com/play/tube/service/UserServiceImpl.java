package com.play.tube.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Doc;
import com.play.tube.Dto.loginIconDto;
import com.play.tube.dao.PhotoDao;
import com.play.tube.dao.UserDao;
import com.play.tube.entity.Photo;
import com.play.tube.entity.Users;
import com.play.tube.exception.LoginException;




@Service
public class UserServiceImpl  {

	@Autowired
	private UserDao userdao;

	@Autowired
	private PhotoDao photoDao;


	public loginIconDto getAllUsers(String email) {
		Users users=userdao.getById(email);
		loginIconDto dto=new loginIconDto(users.getFname(), users.getEmail(), users.getProfilePhoto());
		return dto;
	}

	public Users addUsers(Users user) {
		Users users=userdao.save(user);
		return users;
	}

	public Photo saveFile(MultipartFile file,String email) {
		String docname = file.getOriginalFilename();
		try {
			Photo doc = new Photo(docname,file.getContentType(),file.getBytes(),email);
			return photoDao.save(doc);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Optional<Photo> getFile(String fileId) {
		return photoDao.findById(fileId);
	}

	public Integer login(String email, String password) throws LoginException{
		try {
			Users data=userdao.getById(email);
			if(data.getPassword().equals(password)) {
				return 1;
			}
			else {
				return 0;
			}

		}catch (Exception e) {
//			return 0;
			throw new LoginException(e.getMessage(),e);
			
		}
	}

}

//	public Users login(String email,String password) {
//		Users users=userdao.login(email, password);
//		return users;
//	}



