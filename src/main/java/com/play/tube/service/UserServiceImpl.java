package com.play.tube.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.play.tube.Dto.ItemSendDTO;
import com.play.tube.Dto.loginIconDto;
import com.play.tube.dao.ItemDao;
import com.play.tube.dao.PhotoDao;
import com.play.tube.dao.UserDao;
import com.play.tube.entity.Item;
import com.play.tube.entity.Photo;
import com.play.tube.entity.Users;
import com.play.tube.exception.LoginException;




@Service
public class UserServiceImpl  {

	@Autowired
	private UserDao userdao;

	@Autowired
	private PhotoDao photoDao;
	 @Autowired
	 private ItemDao itemdao;
	 @Autowired
		ItemService itemService;


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
	
	
	
	public void SaveHistory(String user ,Integer itemId ) {
		Users users=userdao.getById(user);
		users.getHistoryItem().add(itemdao.getById(itemId));
		userdao.save(users);
		
	}
	
	public List<ItemSendDTO> getHistory(String user) {
		Users users=userdao.getById(user);
		List<ItemSendDTO>	hitoryItem=itemService.itemPopulator( users.getHistoryItem());
		return hitoryItem;
		
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void deleteOneHistoryItem(String user ,Integer itemId ) {
		Users users=userdao.getById(user);
		users.getHistoryItem().remove(users.getHistoryItem().indexOf(itemId));
		userdao.save(users);
		
	}
	public void clearHistory(String user ) {
		Users users=userdao.getById(user);
		users.getHistoryItem().removeAll(users.getHistoryItem());
		userdao.save(users);
		
	}
	public Integer IsSubscribe(String UserId ,String channelId) {
		Users users=userdao.getById(UserId);
		Integer Status=0;
		if(users.getSubscribed().contains(userdao.getById(channelId))) {
			Status=1;
		}
		return Status;
	}
	
	public long subscribe(String UserId ,String channelId) {
		Users users=userdao.getById(UserId);
		long subscriberNo=0;
		if(!users.getSubscribed().contains(userdao.getById(channelId))) {
		users.getSubscribed().add(userdao.getById(channelId));
		subscriberNo= userdao.getById(channelId).getSubscriber();
		subscriberNo=subscriberNo+1;
		userdao.getById(channelId).setSubscriber(subscriberNo);
		userdao.save(users);
		}
		return subscriberNo;
	}
	public long UnSubscribe(String UserId ,String channelId) {
		Users users=userdao.getById(UserId);
		users.getSubscribed().remove(users.getSubscribed().indexOf(userdao.getById(channelId)) );
		long subscriberNo= userdao.getById(channelId).getSubscriber();
		subscriberNo=subscriberNo-1;
		userdao.getById(channelId).setSubscriber(subscriberNo);
		userdao.save(users);
		return subscriberNo;
	}
	

}

//	public Users login(String email,String password) {
//		Users users=userdao.login(email, password);
//		return users;
//	}



