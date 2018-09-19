package com.winning.dao;

import com.winning.bean.Account;
import org.apache.ibatis.annotations.Param;
import com.winning.common.DaoMapper;

/**
 * @author Administrator
 * 
 */
@DaoMapper
public interface UserMapper {
    //用户登录
	Account login(@Param(value = "account")String account, @Param(value = "pwd")String pwd);
	void register(@Param(value = "user_info")Account userInfo);
	int getUsersByAccount(@Param(value = "account")String account);
	int getRoleIdByName(@Param(value = "role_name")String role_name);
	void updateLoginTime(@Param(value = "user_id")int user_id);
}
