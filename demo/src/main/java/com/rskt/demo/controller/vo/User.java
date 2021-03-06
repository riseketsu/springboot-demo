/**
 * Copyright © 2018 RSKT. All rights reserved. 
 */
package com.rskt.demo.controller.vo;

/**
 * Class:User.java<br>
 *
 * Class Summary：<br>
 * 
 * Class Feature:<br>
 *
 * @Author lishijie
 * @CreateDate 2018-07-05
 *
 */
public class User {
	private String uid;
	private String userId;
	private String userNm;
	private String userPwd;

	/**
	 * userId Get Method.
	 * 
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * userId Set Method.
	 * 
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * userNm Get Method.
	 * 
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}

	/**
	 * userNm Set Method.
	 * 
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	/**
	 * userPwd Get Method.
	 * 
	 * @return the userPwd
	 */
	public String getUserPwd() {
		return userPwd;
	}

	/**
	 * userPwd Set Method.
	 * 
	 * @param userPwd the userPwd to set
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	/**
	 * uid Get Method.
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * uid Set Method.
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
}
