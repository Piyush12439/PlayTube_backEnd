package com.play.tube.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.play.tube.entity.Comment;

import com.play.tube.service.CommentService;

@RestController
@CrossOrigin
@RequestMapping("/tube/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/add")
	public  ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
		
		Comment comments=commentService.addComment(comment);
		
		return new ResponseEntity<Comment>(comments,HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public  ResponseEntity<List<Comment>> getComment() {
		
		List<Comment> comments=commentService.getComments();
		
		return new ResponseEntity<List<Comment>>(comments,HttpStatus.OK);
	}
	
	@PostMapping("/addreply/{id}")
	public  ResponseEntity<Comment> addreply(@PathVariable Integer id,@RequestParam("comment") String comment,@RequestParam("userId") String userId) {
		
		Comment comments=commentService.updateComment(id,comment,userId);
		
		return new ResponseEntity<Comment>(comments,HttpStatus.OK);
	}

}
