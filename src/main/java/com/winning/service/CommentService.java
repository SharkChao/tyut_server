package com.winning.service;

import com.winning.bean.*;
import com.winning.dao.CommentMapper;
import com.winning.dao.MessageMapper;
import com.winning.util.UploadUtil;
import mob.push.api.MobPushConfig;
import mob.push.api.exception.ApiException;
import mob.push.api.model.PushWork;
import mob.push.api.push.PushClient;
import mob.push.api.utils.AndroidNotifyStyleEnum;
import mob.push.api.utils.PlatEnum;
import mob.push.api.utils.PushTypeEnum;
import mob.push.api.utils.TargetEnum;
import org.apache.http.util.TextUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 *
 */
@Service
public class CommentService {
	
	private static final Logger log = Logger.getLogger(CommentService.class);
	@Resource
	private CommentMapper mapper;

	 public Result createComment(Comment comment){
	 	Result result = new Result();
	 	try {
			//单独创建一条评论
			comment.setContent(URLEncoder.encode(comment.getContent(), "utf-8"));
			mapper.createComment(comment);
			if (TextUtils.isEmpty(comment.getFloor_id())){
				//单独开一个楼层
				Floor floor = new Floor();
				floor.setMessage_id(comment.getMessage_id());
				List<String>list = new ArrayList<>();
				list.add(comment.getId());
				floor.setComment_ids(Arrays.toString(list.toArray()));
				mapper.createFloor(floor);
				mapper.updateCommentFloor(comment.getId(),floor.getId());
				result.setCode(1);
			}else {
				//在之前的评论上盖楼
				Floor floor = mapper.getFloor(comment.getMessage_id(),comment.getFloor_id());
				String comment_ids = floor.getComment_ids();
				String s = comment_ids.substring(0, comment_ids.length() - 1) + "," + comment.getId() + "]";
				floor.setComment_ids(s);
				//更新楼层
				mapper.updateFloor(floor);

			}
		}catch (Exception e){
	 		result.setCode(0);
		}
	 	return result;
	 }

	 public List<Floor> getAllFloor(String message_id){
		List<Floor>list = new ArrayList<>();
	 	try {
	 		//根据新闻id获取楼层
			List<Floor> allFloor = mapper.getAllFloor(message_id);
			for (Floor floor:allFloor){
				String comment_ids = floor.getComment_ids();
				String[] split = comment_ids.substring(1, comment_ids.length() - 1).split(",");
				for (String id:split){
					Comment comment = mapper.getCommentById(id);
					comment.setContent(URLDecoder.decode(comment.getContent(), "utf-8"));
					floor.getComments().add(comment);
				}
			}
			list.addAll(allFloor);
		}catch (Exception e){

		}
		return list;
	 }

	public List<Floor> getHotFloor(String message_id){
		List<Floor>list = new ArrayList<>();
		try {
			//根据新闻id获取楼层
			List<Comment> hotComments = mapper.getHotComments(message_id);
				for (Comment hotComment:hotComments){
					Floor floor = mapper.getHotFloor(message_id,hotComment.getFloor_id());
					String comment_ids = floor.getComment_ids();
					String[] split = comment_ids.substring(1, comment_ids.length() - 1).split(",");
					for (String id:split){
						Comment comment = mapper.getCommentById(id);
						comment.setContent(URLDecoder.decode(comment.getContent(), "utf-8"));
						floor.getComments().add(comment);
					}
				list.add(floor);
			}

		}catch (Exception e){

		}
		return list;
	}
	public Result getCommentCount(String message_id){
		Result result = new Result();
		try {
			int commentCount = mapper.getCommentCount(message_id);
			result.setCode(commentCount);
		}catch (Exception e){
			result.setCode(0);
		}
		return  result;
	}

}
