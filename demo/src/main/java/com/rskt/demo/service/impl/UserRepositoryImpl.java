/**
 * Copyright © 2018 RSKT. All rights reserved. 
 */
package com.rskt.demo.service.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import com.rskt.demo.persistent.entity.UserEntity;
import com.rskt.demo.service.repository.UserRepository;

/**
 * Class:UserRepositoryImpl.java<br>
 *
 * Class Summary：<br>
 * 
 * Class Feature:<br>
 *
 * @Author lishijie
 * @CreateDate 2018-08-03
 *
 */
public class UserRepositoryImpl implements UserRepository {
	
	private static AtomicLong counter = new AtomicLong();
	private final ConcurrentMap<String, UserEntity> userMap = new ConcurrentHashMap<String, UserEntity>();

	/**
	 * 用户信息保存
	 * @param entity
	 * @return
	 */
	@Override
	public UserEntity saveUser(UserEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 用户信息删除
	 * @param uid
	 */
	@Override
	public void deleteUserByKey(String uid) {
		// TODO Auto-generated method stub

	}

	/**
	 * 用户信息查询
	 * @param uid
	 * @return
	 */
	@Override
	public UserEntity getUserByKey(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 用户信息查询
	 * @param userId
	 * @return
	 */
	@Override
	public UserEntity getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
