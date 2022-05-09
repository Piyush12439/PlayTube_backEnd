package com.play.tube.Dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class loginIconDto {
	private String UserName;
	private String emailid;
	@Lob
	private byte[] Profilephoto;
}


	