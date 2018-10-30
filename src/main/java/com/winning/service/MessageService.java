package com.winning.service;

import com.winning.bean.Account;
import com.winning.bean.Message;
import com.winning.bean.MessageType;
import com.winning.bean.Result;
import com.winning.dao.MessageMapper;
import com.winning.dao.UserMapper;
import com.winning.util.UploadUtil;
import mob.push.api.MobPushConfig;
import mob.push.api.exception.ApiException;
import mob.push.api.model.PushWork;
import mob.push.api.push.PushClient;
import mob.push.api.utils.AndroidNotifyStyleEnum;
import mob.push.api.utils.PlatEnum;
import mob.push.api.utils.PushTypeEnum;
import mob.push.api.utils.TargetEnum;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

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
		setMobPushService();
    	return result;
	}
	public List<Message>getMessage(int index,int msg_type_id){
    	if (index >= 0){
    		index = index * 50;
		}
      return mapper.getMessages(index,msg_type_id);
    }

    public void setMobPushService(){

		MobPushConfig.appkey = "27e70663ba770";
		MobPushConfig.appSecret = "4a3f850184c893cfa589774676a84f27";
		PushWork push = new PushWork(PlatEnum.all.getCode(),"test content" , PushTypeEnum.notify.getCode()) //初始化基础信息
				.buildTarget(TargetEnum._1.getCode(), null, null, null, null, null)  // 设置推送范围
				.buildAndroid("Android Title", AndroidNotifyStyleEnum.normal.getCode(), null, true, true, true) //定制android样式
				.bulidIos("ios Title", "ios Subtitle", null, 1, null, null, null, null) //定制ios设置
				.buildExtra(1, "{\"key1\":\"value\"}", 1) // 设置扩展信息
				;

		PushClient client = new PushClient();
		try {
			String s = client.sendPush(push);
			System.out.println(s);
		}catch (ApiException e) {
			e.getStatus();	   	   //错误请求状态码
			e.getErrorCode();	       //错误状态码
			e.getErrorMessage();        //错误信息
		}
	}

}
