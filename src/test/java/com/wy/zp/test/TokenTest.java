package com.wy.zp.test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.http.ParseException;

import com.wy.zp.bean.AccessToken;
import com.wy.zp.menu.Button;
import com.wy.zp.menu.ClickButton;
import com.wy.zp.menu.Menu;
import com.wy.zp.menu.ViewButton;
import com.wy.zp.utils.WeixinUtil;

import net.sf.json.JSONObject;

public class TokenTest {
	public static void main(String[] args) throws ParseException, IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
//		AccessToken token = WeixinUtil.getAccessToken();
//		System.out.println( token.getToken()+"\n"+token.getExpiresIn()  );
		
//		String wxtoken = "-GfQW8WpA2Z2kf7vQ_WQ6s7dwqkFoBtJLnTOD8WXbI8oF3C83m41ZwiAUQdtbu0FvYy-Y7w_AQOZ-7NObzXYEjbwa760FaUu_E0YGuwSIMkAVEdAGAPKT";
//		int wxExpiresIn = 7200;
//		//上传图片会需要一个id
//		String path = "C:\\E/1.jpg";
//		String meadiid = WeixinUtil.upload(path, wxtoken, "thumb");
//		System.out.println( meadiid );
		
		Menu menu = new Menu();
		//click 菜单事件
		ClickButton cb11 = new ClickButton();
		cb11.setName("签到");
		cb11.setType("click");
		cb11.setKey("11");
		
		ViewButton vb12 = new ViewButton();
		vb12.setName("view菜单");
		vb12.setType("view");
		vb12.setUrl("http://oneprogrammer.xin/");
		
		ClickButton cb13 = new ClickButton();
		cb13.setName("点击扫码");
		cb13.setType("scancode_push");
		cb13.setKey("13");
		
		
		ClickButton cb14 = new ClickButton();
		cb14.setName("地理位置");
		cb14.setType("location_select");
		cb14.setKey("14");
		
		ClickButton cb15 = new ClickButton();
		cb15.setName("系统拍照");
		cb15.setType("pic_sysphoto");
		cb15.setKey("14");
		
		ClickButton cb16 = new ClickButton();
		cb16.setName("微信相册");
		cb16.setType("pic_weixin");
		cb16.setKey("14");
		
		
		Button button = new Button();
		button.setName("菜单");
		button.setSub_button(new Button[]{cb13,cb14,cb15,cb16});  
		//三个一级菜单
		menu.setButton(new Button[]{cb11,vb12,button});  //子菜单类型不支持扫码，地理位置等？
		
		JSONObject  jsonObject = JSONObject.fromObject(menu);
		String ACCESS_TOKEN = "F4FomG4oFy0jY_dTzm-LVUtRZhAsjTyWkTIIuC08vLwoOYyQQePoa2MO1c_9e5bjvPacKUQ124Gr12SS2TPryrn5ZTZ53O--F5qM7Ousk94d7tR8jFSmiTiWdWw2fPukGTVdAGANAC";
		String outStr = jsonObject.toString();
		String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ACCESS_TOKEN;
		JSONObject  jo = WeixinUtil.doPostStr(url, outStr);
		System.out.println( jo.getInt("errcode") );
		if( jo.getInt("errcode")==0 ){
			System.out.println( "创建菜单成功" );
		}else{
			System.out.println("失败");
		}
		
	}
}
