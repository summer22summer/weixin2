package com.wy.zp.bean;

import java.io.Serializable;

public class AccessToken implements Serializable {

	private static final long serialVersionUID = -7051003285751817330L;
	
	private String Token; //获取到的凭证
	private int ExpiresIn; //凭证有效时间，单位：秒
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public int getExpiresIn() {
		return ExpiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		ExpiresIn = expiresIn;
	}
	
	
	

	
	
	
	
	
	
}
