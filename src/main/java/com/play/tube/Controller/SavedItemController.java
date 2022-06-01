package com.play.tube.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.play.tube.Dto.ItemSendDTO;
import com.play.tube.service.SavedItemService;

import io.swagger.annotations.Api;

@Api
@RestController
@CrossOrigin
@RequestMapping("/tube/saved")
public class SavedItemController {
	
	@Autowired
	SavedItemService savedItemService;
	
	
	@GetMapping("/IsSave/{userid}/{VideoId}")
	public  ResponseEntity<Integer> IsSave(@PathVariable String userid,@PathVariable Integer VideoId ) {
		Integer Status=  savedItemService.IsSaved(userid, VideoId);
		return new ResponseEntity<Integer>(Status,HttpStatus.OK);
	}

	@GetMapping("/SaveVideo/{userid}/{VideoId}")
	public  ResponseEntity<Integer> SaveVideo(@PathVariable String userid,@PathVariable Integer VideoId ) {
		  savedItemService.SaveItems(userid, VideoId);
		return new ResponseEntity<Integer>(1,HttpStatus.OK);
	}
	@GetMapping("/UnSaveVideo/{userid}/{VideoId}")
	public  ResponseEntity<Integer> UnSaveVideo(@PathVariable String userid,@PathVariable Integer VideoId ) {
		  savedItemService.RemoveItemFromSave(userid, VideoId);
		return new ResponseEntity<Integer>(1,HttpStatus.OK);
	}
	@GetMapping("/getSaveVideo/{userid}")
	public  ResponseEntity<List<ItemSendDTO>> getSaveVideo(@PathVariable String userid) {
		List<ItemSendDTO>	savedItem=savedItemService.getSaveItems(userid);
		return new ResponseEntity<List<ItemSendDTO>>(savedItem,HttpStatus.OK);
	}
}
