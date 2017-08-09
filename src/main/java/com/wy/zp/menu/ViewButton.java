package com.wy.zp.menu;

import java.io.Serializable;

public class ViewButton extends Button implements Serializable {
	private static final long serialVersionUID = 8075873375946757689L;
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
