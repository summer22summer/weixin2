package com.wy.zp.menu;

import java.io.Serializable;

public class Menu implements Serializable {

	private static final long serialVersionUID = 5231245510558328298L;
	
	
	private Button [] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}
	
	
	
}
