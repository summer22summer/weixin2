package com.wy.zp.menu;

import java.io.Serializable;

public class ClickButton extends Button implements Serializable {

	private static final long serialVersionUID = -3136880646299478710L;
	
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	

}
