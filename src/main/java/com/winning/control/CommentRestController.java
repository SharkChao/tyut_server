package com.winning.control;

import com.winning.bean.*;
import com.winning.service.CommentService;
import com.winning.service.MessageService;
import com.winning.util.JsonUtil;
import com.winning.util.SpringContextUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class CommentRestController {
	private static final Logger log = Logger.getLogger(CommentRestController.class);

	CommentService commentService = (CommentService) SpringContextUtil.getBean("commentService");

    @PostMapping(value = "comment/createComment")
	String createComment(@RequestBody Comment comment){
		Result result = commentService.createComment(comment);
		JSONObject jsonObject = JSONObject.fromObject(result);
		return JsonUtil.returnSuccessJson(jsonObject.toString(),"");
	}
	@GetMapping(value = "comment/getCommentCount")
	String getCommentCount(@RequestParam(value = "message_id")String message_id){
		Result commentCount = commentService.getCommentCount(message_id);
		JSONObject jsonObject = JSONObject.fromObject(commentCount);
		return JsonUtil.returnSuccessJson(jsonObject.toString(),"");
	}
	@RequestMapping(value = "comment/getAllComment")
	String getAllComment(@RequestParam(value = "message_id")String message_id){
		List<Floor> allFloor = commentService.getAllFloor(message_id);
		JSONArray jsonArray = JSONArray.fromObject(allFloor);
		return JsonUtil.returnSuccessJson(jsonArray.toString(),"");
	}
	@RequestMapping(value = "comment/getHotComment")
	String getHotComment(@RequestParam(value = "message_id")String message_id){
		List<Floor> allFloor = commentService.getHotFloor(message_id);
		JSONArray jsonArray = JSONArray.fromObject(allFloor);
		return JsonUtil.returnSuccessJson(jsonArray.toString(),"");
	}
}