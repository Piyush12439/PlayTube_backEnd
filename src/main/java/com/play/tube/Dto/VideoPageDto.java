package com.play.tube.Dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.Entity;

import javax.persistence.Lob;


import com.play.tube.entity.Comment;

import com.play.tube.entity.Tags;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class VideoPageDto {
	
	private Integer id;
	private String docName;
	
	private String discription;
	private String UserId;
	private Integer views;
	private Date UplodeDate;
	
	private Integer likes;
	private Integer dislikes;
	
	List<Comment> comment=new ArrayList<Comment>();
	
	List<Tags> tag=new ArrayList<Tags>();
	
	private String user;

	@Lob
	private byte[] Video;
	@Lob
	private byte[] ProfilePhoto;


}
