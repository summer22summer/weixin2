package com.wy.zp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.wy.zp.test.City;

public class NewsMessage extends BaseMessage implements Serializable { 

	private static final long serialVersionUID = 2890720852136216061L;
	
	private int ArticleCount;	//	图文消息个数，限制为8条以内
	private List<News> Articles ;  //图文信息
	
	
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<News> getArticles() {
		return Articles;
	}
	public void setArticles(List<News> articles) {
		Articles = articles;
	}
	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
