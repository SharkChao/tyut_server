package com.winning.control;

import com.winning.bean.Account;
import com.winning.bean.Message;
import com.winning.bean.MessageType;
import com.winning.bean.Result;
import com.winning.service.MessageService;
import com.winning.service.UserService;
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
public class MessageRestController {
	private static final Logger log = Logger.getLogger(MessageRestController.class);

	MessageService messageService = (MessageService) SpringContextUtil.getBean("messageService");


	@RequestMapping(value="/message/getMessageType")
	String getMessageType(){
		List<MessageType> messageTypes = messageService.getMessageType();
		JSONArray jsonArray = JSONArray.fromObject(messageTypes);
		return JsonUtil.returnSuccessJson(jsonArray.toString(),"");
	}

	@RequestMapping(value = "/message/createMessage")
	String createMessage(@RequestPart("files") MultipartFile[] files, @RequestPart("Message")Message message){
		Result result = messageService.createMessage(files, message);
		JSONObject json=JSONObject.fromObject(result);
		return json.toString();
	}

    @RequestMapping(value="/message/getMessage")
    String getMessage(@RequestParam("index")int index,@RequestParam("msg_type_id")int msg_type_id){
        List<Message> messages = messageService.getMessage(index,msg_type_id);
        JSONArray jsonArray = JSONArray.fromObject(messages);
        return JsonUtil.returnSuccessJson(jsonArray.toString(),"");
    }
}