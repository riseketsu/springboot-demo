/**
 * Copyright © 2018 RSKT. All rights reserved. 
 */
package com.rskt.demo.service.repository;

import java.util.List;

import com.rskt.demo.persistent.entity.UserEntity;

/**
 * Class:UserRepository.java<br>
 *
 * Class Summary：<br>
 * 
 * Class Feature:<br>
 *
 * @Author lishijie
 * @CreateDate 2018-08-03
 *
 */
public interface UserRepository {

	/**
	 * 用户信息保存
	 * @param entity
	 * @return
	 */
	public UserEntity saveUser(UserEntity entity);

	/**
	 * 用户信息删除
	 * @param uid
	 */
	public void deleteUserByKey(String uid);
	
	/**
	 * 用户信息查询
	 * @param uid
	 * @return
	 */
	public UserEntity getUserByKey(String uid);
	
	/**
	 * 用户信息查询
	 * @param userId
	 * @return
	 */
	public UserEntity getUserByUserId(String userId);
	
	/**
	 * 根据条件获取用户列表信息
	 * @param condition
	 * @return
	 */
	public List<UserEntity> getUserList(UserEntity condition);
}
