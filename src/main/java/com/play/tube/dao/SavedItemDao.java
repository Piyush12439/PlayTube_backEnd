package com.play.tube.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.play.tube.entity.Item;
import com.play.tube.entity.SavedItems;
import com.play.tube.entity.Users;

@Repository

public interface SavedItemDao extends JpaRepository<SavedItems,Integer>{

	
	@Query( value ="Select * from Saved_items where User=?1",
			nativeQuery = true)
	SavedItems getdata(String user);

}
