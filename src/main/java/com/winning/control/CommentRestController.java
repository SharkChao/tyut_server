package com.winning.control;

import com.winning.bean.Comment;
import com.winning.bean.Message;
import com.winning.bean.MessageType;
import com.winning.bean.Result;
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
	@RequestMapping(value = "comment/getAllComment")
	String getAllComment(@RequestParam(value = "message_id")String message_id){
		return "";
	}
}