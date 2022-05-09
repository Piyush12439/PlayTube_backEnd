package com.play.tube.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.play.tube.entity.Tags;

public interface TagDao extends JpaRepository<Tags,Integer>{

}
