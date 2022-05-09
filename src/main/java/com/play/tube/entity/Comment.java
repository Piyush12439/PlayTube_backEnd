package com.play.tube.entity;





import javax.persistence.Id;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@NoArgsConstructor
@AllArgsConstructor

@Getter
@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	public String comment;
	public Integer like_no;
	public Integer dislike_no;
	public String UserId;
	public Date commentDate;
	@OneToMany(cascade = {CascadeType.ALL} ,fetch = FetchType.EAGER)
	@JoinColumn(name="reply")
  private List<Comment> reply;
	
	
	
	
	public Comment(String comment, Integer like_no, Integer dislike_no, List<Comment> reply) {
		super();
		this.comment = comment;
		this.like_no = like_no;
		this.dislike_no = dislike_no;
		this.reply = reply;
	}




	public Comment(String comment, String userId, Date commentDate) {
		super();
		this.comment = comment;
		this.UserId = userId;
		this.commentDate = commentDate;
	}




	


}


	