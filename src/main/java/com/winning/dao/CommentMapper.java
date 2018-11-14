package com.winning.dao;

import com.winning.bean.Comment;
import com.winning.bean.Floor;
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
public interface CommentMapper {
   void createComment(@Param(value = "comment")Comment comment);
   void createFloor(@Param(value = "floor")Floor floor);
   Floor getFloor(@Param(value = "message_id")String message_id);
   void updateFloor(@Param(value = "floor")Floor floor);
}
