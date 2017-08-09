package com.wy.zp.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.wy.zp.bean.News;
import com.wy.zp.bean.NewsMessage;
import com.wy.zp.bean.TextMessage;
import com.wy.zp.utils.XmlAndMap;

public class Test {
	public static void main(String[] args) {
		
        
    	
    	
    	
    	//图文详情
    	News news = new News();
    	news.setTitle("My first!");
    	news.setDescription("哈哈哈哈 测试！");
    	news.setPicUrl("http://119.23.65.165/weixin/images/my.jpg");
    	news.setUrl("http://oneprogrammer.xin/");
    	
    	//图文详情2
    	News news2 = new News();
    	news.setTitle("My ");
    	news.setDescription("哈哈哈哈 ！");
    	news.setPicUrl("http://119.23.65.165/weixin/images/my.jpg");
    	news.setUrl("http://oneprogrammer.xin/");
    	
    	

    	
    	List<News> list = new ArrayList<News>();
    	list.add(news);
    	list.add(news2);	
    	
    	NewsMessage nms = new NewsMessage();
    	//设置回复相关信息
    	nms.setArticleCount( list.size());  //1条图文
    	nms.setArticles(list);  //设置图文
    	nms.setMsgType("147");
    	nms.setToUserName("778");
    	nms.setFromUserName("999");
    	nms.setCreateTime(new Date().getTime());
	
    	

		
    	XStream xstream = new XStream();  
    	xstream.alias("xml", NewsMessage.class);
    	xstream.alias("item", News.class);
    	String str = xstream.toXML(nms);
    	
    	System.out.println( str );
    	
		
		
		
		
		
		
		
		
		
}
}
