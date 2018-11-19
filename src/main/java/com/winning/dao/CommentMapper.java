package com.winning.dao;

import com.winning.bean.*;
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
   Floor getFloor(@Param(value = "message_id")String message_id,@Param(value = "floor_id")String floor_id);
   void updateFloor(@Param(value = "floor")Floor floor);
   List<Floor> getAllFloor(@Param(value = "message_id")String message_id);
   Comment getCommentById(@Param(value = "comment_id")String id,@Param(value = "user_id")String user_id);
   void updateCommentFloor(@Param(value = "comment_id")String comment_id,@Param(value = "floor_id")String floor_id);
   List<Comment> getHotComments(@Param(value = "message_id")String message_id);
   Floor getHotFloor(@Param(value = "message_id")String message_id,@Param(value = "floor_id")String floor_id);
   int getCommentCount(@Param(value = "message_id")String message_id);
   void createPraise(@Param(value = "comment_praise")CommentPraise commentPraise);
   void updateComment(@Param(value = "comment_id")String comment_id);
   int getPraiseCount(@Param(value = "comment_praise")CommentPraise commentPraise);
}
