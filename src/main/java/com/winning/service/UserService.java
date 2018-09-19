package com.winning.service;
import javax.annotation.Resource;

import com.winning.bean.Account;
import com.winning.bean.Result;
import com.winning.util.UploadUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.winning.dao.UserMapper;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Administrator
 *
 */
@Service
public class UserService {
	
	private static final Logger log = Logger.getLogger(UserService.class);
	@Resource
	private UserMapper userMapper;
	
    
	/**
	 * 登录
	 * 
	 * @param account
	 * @param pwd
	 * @return
	 */
    public Account login(String account, String pwd){
    	log.info("方法调用：用户登录");
		Account userInfo = userMapper.login(account, pwd);
    	return userInfo;
    }

    public Result register(MultipartFile file,Account userInfo){
    	Result result = new Result();
		int role_id = userMapper.getRoleIdByName("开发者");
			try {
				String imagePath = UploadUtil.uploadImage(file);
				userMapper.register(userInfo);
				result.setCode(1);
				result.setMessage("注册成功!");

			} catch (Exception e) {
				log.error("上传文件失败，" + e);
				result.setCode(0);
				result.setMessage(e.getMessage());
			}

		return result;
	}


}
