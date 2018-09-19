package com.winning.service;

import com.winning.bean.Account;
import com.winning.bean.Message;
import com.winning.bean.MessageType;
import com.winning.bean.Result;
import com.winning.dao.MessageMapper;
import com.winning.dao.UserMapper;
import com.winning.util.UploadUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 *
 */
@Service
public class MessageService {
	
	private static final Logger log = Logger.getLogger(MessageService.class);
	@Resource
	private MessageMapper mapper;

    public List<MessageType> getMessageType(){
		List<MessageType> messageType = mapper.getMessageType();
		return messageType;
	}

	public Result createMessage(MultipartFile[] files, Message message){
    	List<String>list = new ArrayList<>();
    	for (MultipartFile file:files){
			try {
				String url = UploadUtil.uploadImage(file);
				list.add(url);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		String urls = Arrays.toString(list.toArray());
    	message.setMsg_imgs(urls);
    	mapper.saveMessage(message);
    	Result result = new Result();
    	result.setCode(1);
    	result.setMessage("上传消息成功!");
    	return result;
	}
	public List<Message>getMessage(int index){
    	if (index >= 0){
    		index = index * 50;
		}
      return mapper.getMessages(index);
    }

}
