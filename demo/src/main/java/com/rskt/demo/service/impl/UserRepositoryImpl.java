/**
 * Copyright © 2018 RSKT. All rights reserved. 
 */
package com.rskt.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
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
		if (entity.getUid() == null) {
			long uid = counter.incrementAndGet();
			entity.setUid(String.valueOf(uid));
		}
		this.userMap.put(entity.getUid(), entity);
		return entity;
	}

	/**
	 * 用户信息删除
	 * @param uid
	 */
	@Override
	public void deleteUserByKey(String uid) {
		this.userMap.remove(uid);
	}

	/**
	 * 用户信息查询
	 * @param uid
	 * @return
	 */
	@Override
	public UserEntity getUserByKey(String uid) {
		return this.userMap.get(uid);
	}

	/**
	 * 用户信息查询
	 * @param userId
	 * @return
	 */
	@Override
	public UserEntity getUserByUserId(String userId) {
		for (Entry<String, UserEntity> entry : this.userMap.entrySet()) {
			UserEntity entity = entry.getValue();
			if (entity.getUserId().equals(userId)) {
				return entity;
			}
		}
		return null;
	}
	
	/**
	 * 根据条件获取用户列表信息
	 * @param condition
	 * @return
	 */
	@Override
	public List<UserEntity> getUserList(UserEntity condition) {
		List<UserEntity> result = new ArrayList<UserEntity>();
		if (condition == null) {
			result = new ArrayList<UserEntity>(this.userMap.values());
		} else {
			
		}
		return result;
	}
}
