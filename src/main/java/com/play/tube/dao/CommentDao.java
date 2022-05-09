package com.play.tube.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.play.tube.entity.Comment;



@Repository
public interface CommentDao extends JpaRepository<Comment,Integer>{

}
