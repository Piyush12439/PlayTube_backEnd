package com.play.tube.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.play.tube.Dto.ItemSendDTO;
import com.play.tube.Dto.VideoPageDto;
import com.play.tube.dao.ItemDao;
import com.play.tube.dao.PhotoDao;
import com.play.tube.dao.UserDao;
import com.play.tube.entity.Comment;
import com.play.tube.entity.Item;
import com.play.tube.entity.Likes_Unlikes;

@Service
public class ItemService {

	@Autowired
	private ItemDao dao;
	@Autowired
	private UserDao dao2;
	@Autowired
	private Likes_dislikeService dislikeService;

	public Item addItem( Item item1) {

		Item items=dao.save(item1);

		return items;

	}

	public Optional<Item> getItemById(Integer id) {

		Optional<Item> items=dao.findById(id);

		return items;
	}
	
	public void addCommentsToVideo(Integer id,String comment1,String UserId) {
		Item items=dao.getById(id);
		 
	    Date date = new Date();  
	    System.out.println();
		Comment comment=new Comment(comment1,UserId ,(date));
		items.getComment().add(comment);
		dao.save(items);
		
	}
	
	public void updateLikeDislike(String likes,String email,Integer VideoId) {
		
		Item itemsLike=dao.getById(VideoId);
		Integer flag=0;
		for (Likes_Unlikes likes_Unlikes2 : itemsLike.getLikes()) {
			if(likes_Unlikes2.emailID.equals(email)) {
				likes_Unlikes2.setLikes_Unlikes(likes);
				flag=1;
				break;
			};
		
		} if(flag==0){
			Likes_Unlikes likes_Unlikes=new Likes_Unlikes(email,likes);
			itemsLike.getLikes().add(likes_Unlikes);
		}
		dao.save(itemsLike);
		
	
	}
public String checkStatusLikeDislike(String email,Integer VideoId) {
		
		Item itemsLike=dao.getById(VideoId);
		String status=null;
		for (Likes_Unlikes likes_Unlikes2 : itemsLike.getLikes()) {
			if(likes_Unlikes2.emailID.equals(email)) {
				 status=likes_Unlikes2.getLikes_Unlikes();
				 break;
			};
		}
		return status;
		
	
	}
public  void deleteLikeDislike(String email,Integer VideoId) {
	
	Item itemsLike=dao.getById(VideoId);
	
	for (Likes_Unlikes likes_Unlikes2 : itemsLike.getLikes()) {
		if(likes_Unlikes2.emailID.equals(email)) {
			itemsLike.getLikes().remove(likes_Unlikes2);
			 break;
		};
	}
	dao.save(itemsLike);
	

}

	
	public  List<ItemSendDTO> sendItemById() {
		List<ItemSendDTO> item=itemPopulator(dao.findAll()) ;
		return item;
		
		
	}
	
	public VideoPageDto getVideoById(Integer id) {
		Item items=dao.getById(id);
		VideoPageDto data=VideoPopulator(items);
		return data;
		
		
	}
	
	private VideoPageDto VideoPopulator(Item items) {
		VideoPageDto videoPageDto=new VideoPageDto();
		videoPageDto.setId(items.getId());
		videoPageDto.setDocName(items.getDocName());
		videoPageDto.setDiscription(items.getDiscription());
		videoPageDto.setViews(items.getViews());
		videoPageDto.setUplodeDate(items.getUplodeDate());
		Integer like=0;
		Integer dislike=0;
		List<Likes_Unlikes> likes_Unlikes=items.getLikes();
		for (Likes_Unlikes likes_Unlikes2 : likes_Unlikes) {
			if(likes_Unlikes2.Likes_Unlikes.equals("1")) {
				 like+=1;
			}else if(likes_Unlikes2.Likes_Unlikes.equals("0")) {
				dislike+=1;
			}
		}
		
		videoPageDto.setLikes(like);
		videoPageDto.setDislikes(dislike);
		videoPageDto.setComment(items.getComment());
		videoPageDto.setTag(items.getTag());
		videoPageDto.setUser(items.getUser().getFname()+" "+(items.getUser().getLname()!=null?items.getUser().getLname():" "));
		videoPageDto.setUserId(items.getUser().getEmail());
		videoPageDto.setVideo(items.getData());
		videoPageDto.setProfilePhoto(items.getUser().getProfilePhoto());
		return videoPageDto;
	}

	public List<ItemSendDTO> itemPopulator(List<Item> item) {
		
		List<ItemSendDTO> resultItem=new ArrayList<ItemSendDTO>();

		for (Item items : item) {
			ItemSendDTO dto=new ItemSendDTO();
			dto.setId(items.getId());
			dto.setDocName(items.getDocName());
			dto.setUser(items.getUser().getFname()+" "+items.getUser().getLname());
			dto.setPhoto(items.getUser().getProfilePhoto());
			dto.setVideo_id(items.getId());
			dto.setViews(items.getViews());
			dto.setThumbnail(items.getThumbnail());
			dto.setDays(items.getUplodeDate());
			resultItem.add(dto);
		}
		
		return resultItem;
	}

}
