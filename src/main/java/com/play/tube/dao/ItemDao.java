package com.play.tube.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.play.tube.entity.Item;


@Repository
public interface ItemDao extends JpaRepository<Item,Integer>{
	
	@Query("Select docName from Item where docType=?1")
	List<String> getdata( String type);
	
	}
