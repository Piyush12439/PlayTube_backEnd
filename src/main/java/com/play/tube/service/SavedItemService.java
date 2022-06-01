package com.play.tube.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.play.tube.Dto.ItemSendDTO;
import com.play.tube.dao.ItemDao;
import com.play.tube.dao.SavedItemDao;
import com.play.tube.dao.UserDao;
import com.play.tube.entity.Item;
import com.play.tube.entity.SavedItems;

@Service
public class SavedItemService {
	@Autowired
	SavedItemDao dao;
	
	@Autowired
	ItemDao itemDao;
	
	@Autowired 
	UserDao userDao;
	
	@Autowired
	ItemService itemService;
	
	public List<ItemSendDTO> getSaveItems(String userId) {
		SavedItems savedItems=dao.getdata("piyushchaurasia12@gmail.com");
		List<ItemSendDTO>	savedItem=itemService.itemPopulator(savedItems.getItems());
		return savedItem;
	}
	
	public void SaveItems(String userId, Integer itemId) {
		SavedItems savedItems=dao.getdata(userId);
		if(savedItems.equals(null)) {
			SavedItems savedItem=new SavedItems();
			savedItem.setUsers(userDao.getById(userId));
			savedItem.getItems().add(itemDao.getById(itemId));
			dao.save(savedItem);
			
		}
		else {
			savedItems.getItems().add(itemDao.getById(itemId));
			dao.save(savedItems);
		}
	}
	
	public void RemoveItemFromSave(String userId, Integer itemId) {
		SavedItems savedItems=dao.getdata(userId);
//		System.out.println(savedItems);
		savedItems.getItems().remove(savedItems.getItems().indexOf(itemDao.getById(itemId))) ;
		dao.save(savedItems);
		
	}
	public Integer IsSaved(String userId, Integer itemId) {
		SavedItems savedItems=dao.getdata(userId);
		Integer res=0;
		if(savedItems.getItems().contains(itemDao.getById(itemId))) {
			res=1;
		}
		
		
		return res;
		
	}

}
