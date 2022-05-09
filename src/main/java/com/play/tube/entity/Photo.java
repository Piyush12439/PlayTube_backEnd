package com.play.tube.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

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
public class Photo {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String docName;
	private String docType;
	@Lob
	private byte[] data;
	@Id
	private String email;
	public Photo(String docName, String docType, byte[] data, String email) {
		super();
		this.docName = docName;
		this.docType = docType;
		this.data = data;
		this.email = email;
	}
	
	
	
}

	