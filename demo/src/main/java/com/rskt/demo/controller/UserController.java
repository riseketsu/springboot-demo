/**
 * Copyright © 2018 RSKT. All rights reserved. 
 */
package com.rskt.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.rskt.demo.persistent.entity.UserEntity;
import com.rskt.demo.service.repository.UserRepository;

/**
 * Class:UserController.java<br>
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
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 查询所有用户信息
	 * @param model
	 * @return
	 */
	@GetMapping
	public ModelAndView list(Model model) {
		List<UserEntity> userList = userRepository.getUserList(null);
		model.addAttribute("userList", userList);
		model.addAttribute("title", "用户管理");
		return new ModelAndView("users/list", "userModel", model);
	}
}
