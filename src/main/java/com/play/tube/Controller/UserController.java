package com.play.tube.Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.play.tube.Dto.ItemSendDTO;
import com.play.tube.Dto.loginIconDto;
import com.play.tube.entity.Item;
import com.play.tube.entity.Photo;
import com.play.tube.entity.Users;
import com.play.tube.exception.LoginException;
import com.play.tube.service.UserServiceImpl;


import io.swagger.annotations.Api;



@Api
@RestController
@CrossOrigin
@RequestMapping("/tube")
public class UserController {
	

	@Autowired
	private UserServiceImpl userService;
	
	
	@GetMapping("/all/{fileId}")
	public  ResponseEntity<loginIconDto> getUser(@PathVariable String fileId) {
		loginIconDto users=userService.getAllUsers(fileId);
		return new ResponseEntity<loginIconDto>(users,HttpStatus.OK);
	}
	
	@GetMapping("/login")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public  ResponseEntity<Integer> login(@RequestParam("email") final String email,
            @RequestParam("password") final String password)throws LoginException {
//		System.out.println(email);
//		System.out.println(password);
		Integer users = null;
		try {
			users = userService.login(email, password);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(users,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public  ResponseEntity<Users> addUser(@RequestParam("fname") String fname,@RequestParam("lname") String lname,@RequestParam("email") String email
			,@RequestParam("mobile") Long mobile,@RequestParam("gender") String gender,@RequestParam("dob") String dob,@RequestParam("address") String address,
			@RequestParam("city") String city,@RequestParam("pin") Long pin,@RequestParam("state") String state,@RequestParam("password") String password,@RequestParam("photo") MultipartFile photo) throws IOException, ParseException {
	
		Date date=new SimpleDateFormat("yyyy/MM/dd").parse(dob);  
//		System.out.println(date);
		Users user=new Users( fname,  lname, email,  mobile, gender,  date, address,
			 city, pin, state, password,photo.getBytes());
		Users users=userService.addUsers(user);

		return new ResponseEntity<Users>(users,HttpStatus.OK);
	}
	
	@GetMapping("/saveHistory/{userid}/{itemId}")
	public  ResponseEntity<String> saveHistory(@PathVariable String userid ,@PathVariable Integer itemId ) {
		userService.SaveHistory(userid, itemId);
		return new ResponseEntity<String>("Saved",HttpStatus.OK);
	}
	
	@GetMapping("/getHistory/{userid}")
	public  ResponseEntity<List<ItemSendDTO>> getHistory(@PathVariable String userid  ) {
		List<ItemSendDTO> historyitem= userService.getHistory(userid);
		return new ResponseEntity<List<ItemSendDTO>>(historyitem,HttpStatus.OK);
	}
	
	
	@GetMapping("/deleteHistory/{userid}/{itemId}")
	public  ResponseEntity<String> deleteHistory(@PathVariable String userid ,@PathVariable Integer itemId ) {
		userService.deleteOneHistoryItem(userid, itemId);
		return new ResponseEntity<String>(itemId+" Deleted",HttpStatus.OK);
	}
	
	@GetMapping("/clearHistory/{userid}")
	public  ResponseEntity<String> clearHistory(@PathVariable String userid ) {
		userService.clearHistory(userid);
		return new ResponseEntity<String>(userid+" history cleared",HttpStatus.OK);
	}
	
	@GetMapping("/subscribe/{userid}/{channelId}")
	public  ResponseEntity<Long> subscribe(@PathVariable String userid,@PathVariable String channelId ) {
		Long SubscribersNo=  userService.subscribe(userid, channelId);
		return new ResponseEntity<Long>(SubscribersNo,HttpStatus.OK);
	}
	
	@GetMapping("/unsubscribe/{userid}/{channelId}")
	public  ResponseEntity<Long> unsubscribe(@PathVariable String userid,@PathVariable String channelId ) {
		Long SubscribersNo=  userService.UnSubscribe(userid, channelId);
		return new ResponseEntity<Long>(SubscribersNo,HttpStatus.OK);
	}
	
	@GetMapping("/IsSubscribe/{userid}/{channelId}")
	public  ResponseEntity<Integer> IsSbscribe(@PathVariable String userid,@PathVariable String channelId ) {
		Integer Status=  userService.IsSubscribe(userid, channelId);
		return new ResponseEntity<Integer>(Status,HttpStatus.OK);
	}
	
	
//	@PostMapping("/uploadFiles")
//	public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,@RequestParam("id") String id) {
//		for (MultipartFile file: files) {
////			System.out.println(id);
//			userService.saveFile(file,id);
//		}
//		
//		return "redirect:/";
//	}
//	
//	@GetMapping("/downloadFile/{fileId}")
//	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId){
//		Photo doc = userService.getFile(fileId).get();
//		byte[] encode = Base64.getEncoder().encode(doc.getData());
//		System.out.println(encode);
//		return ResponseEntity.ok()
//				.contentType(MediaType.parseMediaType(doc.getDocType()))
//				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
//				.body(new ByteArrayResource(encode));
//	}

}
