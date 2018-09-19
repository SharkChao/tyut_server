package com.winning.control;

import com.winning.bean.Account;
import com.winning.bean.Result;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.winning.service.UserService;
import com.winning.util.JsonUtil;
import com.winning.util.SpringContextUtil;
import org.springframework.web.multipart.MultipartFile;

@RestController
@EnableAutoConfiguration
public class UserRestController {
	private static final Logger log = Logger.getLogger(UserRestController.class);

	UserService userService = (UserService) SpringContextUtil.getBean("userService");
	
	//1.用户登录
    @RequestMapping(value="/user/login")
	String login(@RequestParam("account") String  account ,@RequestParam("pwd") String pwd) {
		log.info("login方法调用:"+account);
		try{
			Account user = userService.login(account,pwd);
			JSONObject json=JSONObject.fromObject(user);
			return JsonUtil.returnSuccessJson(json.toString(),"");
		}catch(Exception e){
			log.error("login方法:"+e);
			return JsonUtil.returnFailureJson(e.getMessage(), "101");
		}
		
	}
	//2.用户注册
	@RequestMapping("/user/register")
	@ResponseBody
	String register(@RequestPart("file") MultipartFile  file, @RequestPart("user_info")Account userInfo){
		log.info("register方法调用:");
		try{
			Result result = userService.register(file,userInfo);
			JSONObject json=JSONObject.fromObject(result);
			return json.toString();
		}catch(Exception e){
			log.error("register方法:"+e);
			return JsonUtil.returnFailureJson(e.getMessage(), "101");
		}
	}

}