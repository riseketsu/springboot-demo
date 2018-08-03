/**
 * Copyright © 2018 RSKT. All rights reserved. 
 */
package com.rskt.demo.controller.vo;

import javax.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement
public class UserXml {
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
}
