package com.play.tube.entity;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String docName;
	private String docType;
	private String discription;
	private Boolean isPublicVedios;
	private Integer views;
	private Date UplodeDate;
	
	@ManyToMany(mappedBy = "items")
	
	List<SavedItems> saveItems=new ArrayList<SavedItems>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="likes")
	List<Likes_Unlikes> likes=new ArrayList<Likes_Unlikes>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="Comments")
	
	List<Comment> comment=new ArrayList<Comment>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="likes")
	List<Tags> tag=new ArrayList<Tags>();
	

	
		
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="user")
	private Users user;

	@Lob
	private byte[] data;
	@Lob
	private byte[] thumbnail;


	
	
}
