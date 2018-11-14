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
				result.setCode(1);
			}else {
				//在之前的评论上盖楼

				//1.先获取楼层
				Floor floor = mapper.getFloor(comment.getMessage_id());
				String comment_ids = floor.getComment_ids();
				String s = comment_ids.substring(0, comment_ids.length() - 1) + "," + comment.getId() + "]";
				floor.setComment_ids(s);
				//更新楼层

			}
		}catch (Exception e){
	 		result.setCode(0);
		}
	 	return result;
	 }

	 public List<Comment> getAllComment(String message_id){
	 	Result result = new Result();

	 	try {
	 		//根据新闻id获取楼层

		}catch (Exception e){

		}
		return new ArrayList<>();
	 }


}
