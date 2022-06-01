package com.play.tube.Dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.play.tube.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Entity
public class ItemSendDTO {
	private Integer id;
	private String docName;
	private String user;
	private Integer views;
	private Date days;
	private String userId;
	@Lob
	private byte[] photo;
	@Lob
	private Integer Video_id;
	@Lob
	private byte[] thumbnail;

	
	
}