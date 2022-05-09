package com.play.tube.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.play.tube.entity.Likes_Unlikes;

@Repository
public interface LikeDao extends JpaRepository<Likes_Unlikes,Integer>{

//	List<User> findByNameOrBirthDate(String name, ZonedDateTime birthDate);
//	Likes_Unlikes findByEmailID(String email );

}
