package com.play.tube.Controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.play.tube.Dto.ItemSendDTO;
import com.play.tube.Dto.VideoPageDto;
import com.play.tube.dao.ItemDao;
import com.play.tube.dao.UserDao;
import com.play.tube.entity.Item;
import com.play.tube.entity.Likes_Unlikes;
import com.play.tube.entity.Photo;
import com.play.tube.entity.Tags;
import com.play.tube.entity.Users;
import com.play.tube.service.ItemService;
import com.play.tube.service.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@CrossOrigin
@RequestMapping("/tube/items")
public class ItemController {
	@Autowired
	private ItemService service;

	@Autowired
	private UserDao dao2;

	@Autowired
	private ItemDao dao;



	@PostMapping("/uploaditems")
	public int uploadMultipleFiles(@RequestParam("containt") MultipartFile containt,@RequestParam("title") String title
			,@RequestParam("type") String type,@RequestParam("tags") String tags
			,@RequestParam("ispublic") Boolean ispublic,@RequestParam("discription") String discription,@RequestParam("User") String UserId
			,@RequestParam("UplodeDate") Date UplodeDate,@RequestParam("thumbnail") MultipartFile thumbnail) throws IOException
	{
		Item item=new Item();

		item.setData(containt.getBytes());
		item.setDocName(title);
		item.setDocType(type);
		System.out.println(ispublic);
		item.setIsPublicVedios(ispublic);
		item.setDiscription(discription);
		//		item.setTag(tags);
		item.setUplodeDate(UplodeDate);
		item.setThumbnail(thumbnail.getBytes());

		item.setUser(dao2.getById(UserId));
		

		service.addItem(item);
		return 1;
	}
	@GetMapping("/homeVideo")
	public ResponseEntity<List<ItemSendDTO>> downloadFile(){

		List<ItemSendDTO> res=service.sendItemById();
		return ResponseEntity.ok()
				.body(res);
	}

	@ApiOperation(consumes = "region_id",
			produces = "RegionMaster",
			notes = "http://localhost:8082/usm/tube/items/all",
			value = "Get All Region",						
			response = Item.class,
			nickname = "get data by Id"
			)
	@GetMapping("/itemData")
	public ResponseEntity<Item> itemFile(){

		Item res=dao.getById(8);
		return ResponseEntity.ok()
				.body(res);
	}

	@ApiOperation(value = "add new Region",
			consumes = "RegionMaster",
			produces = "Region id",
			response = Item.class,
			nickname = "Add Region",
			notes = "http://localhost:8082/usm/tube/items/add"
			)

	@PostMapping("/add")
	public ResponseEntity<Item> additemFile(@RequestBody Item item){

		Item res=dao.save(item);
		return ResponseEntity.ok()
				.body(res);
	}

	@PostMapping("/addComments")
	public ResponseEntity<String> addcomments(@RequestParam("id") Integer id,@RequestParam("Comment") String Comment,@RequestParam("UserId") String UserId){
		System.out.println("addComments");
		service.addCommentsToVideo(id,Comment,UserId);
		return ResponseEntity.ok()
				.body("Added");
	}
	
	@GetMapping("/homeVideo/{id}")
	public ResponseEntity<VideoPageDto> GetVideoById(@PathVariable Integer id){

		VideoPageDto res=service.getVideoById(id);
		return ResponseEntity.ok()
				.body(res);
	}
	
	@PostMapping("/UpdateLikes")
	public  void UpdateLikes(@RequestParam("id") Integer id,@RequestParam("UserEmail") String Useremail,
			@RequestParam("LikesStatus") String LikesStatus) {
	 service.updateLikeDislike(LikesStatus, Useremail, id);
		
				
	}
	
	@GetMapping("/likeStatus/{id}/{UserEmail}")
	public ResponseEntity<String> GetVideoById(@PathVariable Integer id,
			@PathVariable String UserEmail){

		String status=service.checkStatusLikeDislike( UserEmail, id);
		return ResponseEntity.ok()
				.body(status);
	}
	
	@GetMapping("/deletelike/{id}/{UserEmail}")
	public void deletelikeById(@PathVariable Integer id,
			@PathVariable String UserEmail){

		service.deleteLikeDislike( UserEmail, id);
		
	}

}
