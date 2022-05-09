package com.play.tube.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
@Entity
public class Likes_Unlikes {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	public String emailID;
	public String Likes_Unlikes;
	public Likes_Unlikes(String emailID, String likes_Unlikes) {
		
		this.emailID = emailID;
		this.Likes_Unlikes = likes_Unlikes;
	}
	
	
	
//	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinColumn(name="items")
//	public Item items;
	
	}