package com.play.tube.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.play.tube.dao.ItemDao;
import com.play.tube.dao.SavedItemDao;
import com.play.tube.dao.UserDao;
import com.play.tube.entity.Item;
import com.play.tube.entity.SavedItems;
import com.play.tube.entity.Users;
import com.play.tube.service.SavedItemService;

import io.swagger.annotations.Api;

@Api
@RestController
@CrossOrigin
@RequestMapping("/tube")

	

public class TestController {
	@Autowired
	SavedItemDao dao;
	
	@Autowired
	SavedItemService SaveditemService;
	
	@GetMapping("/{user}/{id}")
	public void downloadFile(@PathVariable String user,@PathVariable Integer id){
//		System.out.println(user);
//		System.out.println(id);
		SaveditemService.SaveItems(user, id);
		
//		 List<Integer> item=new ArrayList<Integer>();
//		
//		SavedItems savedItems=dao.getdata("piyushchaurasia12@gmail.com");
////		item.add(8);
////		item.add(9);
////		savedItems.getItems().add(itemDao.getById(8));
////		savedItems.getItems().add(itemDao.getById(9));
////		dao.save(savedItems);
//		savedItems.getItems().indexOf(savedItems);
//		dao.save(savedItems);
//		for (Item integer : savedItems.getItems()) {
//			item.add(integer.getId());
//		}
//		System.out.println(savedItems);
////		List<String> data =dao.getdata("Video");
////		System.out.println(data);
//		return ResponseEntity.ok()
//				.body(item);
	}

}
