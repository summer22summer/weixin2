package com.wy.zp.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wy.zp.bean.Image;
import com.wy.zp.bean.ImageMessage;
import com.wy.zp.bean.Music;
import com.wy.zp.bean.MusicMessage;
import com.wy.zp.bean.News;
import com.wy.zp.bean.NewsMessage;
import com.wy.zp.bean.TextMessage;
import com.wy.zp.bean.Video;
import com.wy.zp.bean.VideoMessage;
import com.wy.zp.bean.Voice;
import com.wy.zp.bean.VoiceMessage;
import com.wy.zp.utils.CheckUtil;
import com.wy.zp.utils.XmlAndMap;

//@WebServlet("weixin.action")
public class WeixinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//记录签到次数
	int t =0;
	//用于签单记录并判断是否签到签到
	TreeSet ts = new TreeSet();  //记录签到的人的排序
	Map<String,Object> usermap = new HashMap<String,Object>();
	//正确的做法应该是存放在数据库
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Map<String,Object> map = new HashMap<String,Object>();
		//确认是来自微信服务器
		String signature = request.getParameter("signature");	//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		String timestamp = request.getParameter("timestamp");  //时间戳
		String nonce = request.getParameter("nonce");		 //随机数
		String echostr = request.getParameter("echostr");	 //随机字符串

		//如果请求来自微信则返回echostr给微信 用来验证成功
		PrintWriter out = response.getWriter();
		if( CheckUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr); 
		}
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        
	        
	        
	        //将传过来xml转换为map
	        Map<String, String> map = XmlAndMap.xmlToMap(request);
	        
	        System.out.println( "收到用户发来的信息:"+map );
	        
	        PrintWriter out = response.getWriter();
	        //获取来自请求的信息
	        String toUserName = map.get("ToUserName");
	        String fromUserName = map.get("FromUserName");
	        String msgType = map.get("MsgType");
	        String content = map.get("Content");
	        //发送给用户
	        String message = null;
	        //判断用户发送信息类型
	        if (msgType.equals( "text" )) {
//	            TextMessage text = new TextMessage();
//	            text.setToUserName(fromUserName);
//	            text.setFromUserName(toUserName); 
//	            text.setMsgType("text");   //setMsgType("text");  //返回的类型
//	            text.setCreateTime(  new Date().getTime() );
//
//	            text.setContent("你发送的消息是 ： " + content);
//	            message = XmlAndMap.textMessageToXml(text);
//		        out.print(message);
//		        out.flush();
//		        out.close();
//	            System.out.println(message);
	        	
		        /*
		         * 回复图片系信息
		         */
		        if("图片".equals(content)) {
		        	//图片id
		        	String id="zkEEIArTS5FJGY4NMfdxywJ0LwuuDo2kUWwBQ-CyHN_6o9m8KOMy_uKxUYOP0P6U";
		        	
		        	Image image = new Image();
		        	image.setMediaId(id);
		        	
		        	ImageMessage im = new ImageMessage();
		        	im.setToUserName(fromUserName);
		        	im.setFromUserName(toUserName);
		        	im.setCreateTime(new Date().getTime());
		        	im.setMsgType("image");
		        	
		        	im.setImage(image);
		        	
		        	String imagemsg = XmlAndMap.imageMessageToXml(im);
		        	System.out.println( imagemsg );
		        	
		        	out.print(imagemsg);
		        	out.flush();
		 	        out.close();
		        }
	        	
		        //如果回复的信息为1  则回复图文信息
		        String newsmessage = null;
		        if("1".equals(content)){
		        	//回复的图文
		        	NewsMessage nms = new NewsMessage();
		        	//图文详情
		        	News news = new News();
		        	news.setTitle("My first!");
		        	news.setDescription("哈哈哈哈 测试！");
		        	
		        	news.setPicUrl("http://119.23.65.165/weixin/images/my.jpg");
		        	news.setUrl("http://mp.weixin.qq.com/s/cTEh0It6yXNd0LawoV4vcA");
		        	
		        	
		        	List<News> list = new ArrayList<News>();
		        	list.add(news);
		        	
		        	
		        	//设置回复相关信息
		        	nms.setArticleCount(1);  //1条图文
		        	nms.setMsgType("news");
		        	nms.setToUserName(fromUserName);
		        	nms.setFromUserName(toUserName);
		        	nms.setCreateTime(new Date().getTime());
		        	nms.setArticles(list);  //设置图文
		        	
		        	newsmessage = XmlAndMap.newsMessageToXml(nms);
		        	
		        	System.out.println( newsmessage );
		        	
		        	out.print(newsmessage);
		        	out.flush();
		 	        out.close();
		        }
	        	
	        	
		        String voiceMessage=null;
		        if("语音".equals(content)) {
		        	String id = "9DolaTqjzi8nX9h6H-l0rp-GCJG5WG4tD29L93T8pkgDX58gbcmd9YerWYv3JkIa";
		        	
		        	VoiceMessage vm = new VoiceMessage();
		        	Voice v = new Voice();
		        	v.setMediaId(id);
		        	
		        	vm.setFromUserName(toUserName);
		        	vm.setToUserName(fromUserName);
		        	vm.setCreateTime(new Date().getTime());
		        	vm.setMsgType("voice");
		        	vm.setVoice(v);
		        	
		        	voiceMessage = XmlAndMap.voiceMessageToXml(vm);
		        	out.print(voiceMessage);
		        	out.flush();
		 	        out.close();
		        
		        }
		        
		        String videoMessage=null;
		        if("视频".equals(content)) {
		        	String id="TJR4umbqiK72k7l_flZ4vItpva7s27e2A6xEPxmUkERsHn5TaJEVM9PiwOCgCmt3";
		        	
		        	VideoMessage vm = new VideoMessage();
		        	
		        	Video v = new Video();
		        	v.setMediaId(id);
		        	v.setTitle("魅蓝呀");
		        	v.setDescription("魅蓝note3");
		        	
		        	vm.setFromUserName(toUserName);
		        	vm.setToUserName(fromUserName);
		        	vm.setCreateTime(new Date().getTime());
		        	vm.setMsgType("video");
		        	vm.setVideo(v);
		        	
		        	videoMessage = XmlAndMap.videoMessageToXml(vm);
		        	System.out.println(   videoMessage );
		        	out.print(videoMessage);
		        	out.flush();
		 	        out.close();
		        	
		        }
		        String musicMessage = null;
		        if("音乐".equals(content)) {
		        	//缩略图id 需要上传
		        	String id="e1J7XJfKbY3l-grkacWlqPWfdjMqPbx1WardQAyFsfHX7FVpMS7PYLw_aUuT4-Y0";
		        	
		        	
		        	Music m = new Music();
		        	m.setThumbMediaId(id);
		        	m.setTitle("偏爱");
		        	m.setDescription("对你偏爱~");
		        	m.setMusicURL("http://6e82af61.ngrok.io/weixin/love.mp3");
		        	m.setHQMusicUrl("http://6e82af61.ngrok.io/weixin/love.mp3");
		        	
		        	MusicMessage mm = new MusicMessage();
		        	mm.setFromUserName(toUserName);
		        	mm.setToUserName(fromUserName);
		        	mm.setCreateTime(new Date().getTime());
		        	mm.setMsgType("music");
		        	mm.setMusic(m);
		        	
		        	musicMessage = XmlAndMap.musicMessageToXml(mm);
		        	System.out.println( musicMessage );
		        	
		        	out.print(musicMessage);
		        	out.flush();
		        	out.close();
		        	
		        }
		        
		     //如果是菜单点击事件
	        }else if("event".equals(msgType)){
	        	
//	        	if("".equals(""))
	        	
	        	ts.add(fromUserName); //储存点击签到的人
	        	//如果没有改用户则增加
	        	if( usermap.containsKey(fromUserName)){ //第一次为false 
//	        		usermap.put(fromUserName, 2); //第二次签到
    	            TextMessage text = new TextMessage();
    	            text.setToUserName(fromUserName);
    	            text.setFromUserName(toUserName); 
    	            text.setMsgType("text");     //返回的类型
    	            text.setCreateTime(  new Date().getTime() );
    	            text.setContent("你今天已签到！");	
    	            message = XmlAndMap.textMessageToXml(text);
    		        out.print(message);
    		        out.flush();
    		        out.close();
	        		
	        		
	        	}else{
	        		usermap.put(fromUserName, 1); //第一次签到
	        		
    	            TextMessage text = new TextMessage();
    	            text.setToUserName(fromUserName);
    	            text.setFromUserName(toUserName); 
    	            text.setMsgType("text");     //返回的类型
    	            text.setCreateTime(  new Date().getTime() );
    	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	            text.setContent("签到成功! 签到时间为:"+sdf.format(new Date()));	
    	            message = XmlAndMap.textMessageToXml(text);
    		        out.print(message);
    		        out.flush();
    		        out.close();
	        	}
	        	
	        	
	        	
	        
	        	
	        	
	        	
	       

	        		
	        	
	        	
	        	
	        	
	        }
	        
	        


	        

		
		
		
	}

}
