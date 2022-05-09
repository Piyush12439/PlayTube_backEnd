package com.play.tube.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.play.tube.entity.Item;


@Repository
public interface ItemDao extends JpaRepository<Item,Integer>{
	}
