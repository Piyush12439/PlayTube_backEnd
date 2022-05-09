package com.play.tube.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.play.tube.entity.Users;



@Repository
public interface UserDao extends JpaRepository<Users,String>{
	
//	@Query("SELECT * FROM Users u WHERE u.email = ?1 and u.password = ?2")
//	Users login(String email, String password);

}



