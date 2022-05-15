package com.play.tube.service;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.play.tube.dao.CommentDao;
import com.play.tube.entity.Comment;




@Service
public class CommentService {
	
	@Autowired
	private CommentDao commentDao;
	
	public Comment addComment(Comment comment) {
		
		Comment comments=commentDao.save(comment);
		
		return comments;
		
	}
	
	public java.util.List<Comment> getComments() {
		
		java.util.List<Comment> comments=commentDao.findAll();
		
		return comments;
		
	}

	public Comment updateComment(Integer id,String comment,String userId) {
		Comment previousComment=commentDao.getById(id);
		Date date=new Date();
		Comment comment2=new Comment(comment,userId,date);
		previousComment.getReply().add(comment2);
		
		Comment comment3=commentDao.save(previousComment);
		return comment3;
	}
	
	
	

}
