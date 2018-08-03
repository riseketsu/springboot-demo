/**
 * Copyright © 2018 RSKT. All rights reserved. 
 */
package com.rskt.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rskt.demo.controller.vo.User;
import com.rskt.demo.controller.vo.UserXml;

/**
 * Class:User.java<br>
 *
 * Class Summary：SpringBoot Learning Class<br>
 * 
 * Class Feature: SpringBoot Learning Class<br>
 *
 * @Author lishijie
 * @CreateDate 2018-07-05
 *
 */
@RestController
public class RsktController {
	
	/**
	 * 获取User信息
	 * 
	 * @return User
	 */
	@RequestMapping("/getUser")
	public User getUser() {
		User user = new User();
		user.setUserId("217027");
		user.setUserNm("李世杰");
		user.setUserPwd("juki7788");
		return user;
	}
	
	/**
	 * 获取User信息
	 * 
	 * @return User
	 */
	@RequestMapping("/getUserXml")
	public UserXml getUserXml() {
		UserXml user = new UserXml();
		user.setUserId("217027");
		user.setUserNm("李世杰");
		user.setUserPwd("juki7788");
		return user;
	}
}
