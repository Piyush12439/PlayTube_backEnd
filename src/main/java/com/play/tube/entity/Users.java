package com.play.tube.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Users {
	
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String fname;
	private String lname;
	@Id
	private String email;
	private Long mobile;
	private String gender;
	private Date dob;
	private String address;
	private String city;
	private Long pin;
	private Long subscriber=(long) 0;
	private String state;
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="likes")
	List<Item> item=new ArrayList<Item>();
	
	@OneToOne(mappedBy = "users")
	private SavedItems saveditems;
	
	@ManyToMany
	@JoinColumn(name="Items")
	List<Item> HistoryItem=new ArrayList<Item>();
	
	@ManyToMany
	@JoinColumn(name="Subscribed")
	List<Users> Subscribed=new ArrayList<Users>(); 
	
	@Lob
	private byte[] profilePhoto;
	public Users(String fname, String lname, String email, Long mobile, String gender, Date dob, String address,
			String city, Long pin, String state, String password,  byte[] profilePhoto) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
		this.city = city;
		this.pin = pin;
		this.state = state;
		this.password = password;
	
		this.profilePhoto = profilePhoto;
	}
	
	

//	@OneToMany(mappedBy = "user")
//    private List<Item> item;
	
	
	

	
	

}
