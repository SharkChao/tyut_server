package com.winning.dao;

import com.winning.bean.Account;
import com.winning.bean.Message;
import com.winning.bean.MessageType;
import com.winning.common.DaoMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * 
 */
@DaoMapper
public interface MessageMapper {
    //用户登录
	List<MessageType> getMessageType();
	void saveMessage(@Param(value = "message")Message message);
	List<Message> getMessages(@Param(value = "index")int index);
}
