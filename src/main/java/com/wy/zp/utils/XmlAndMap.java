package com.wy.zp.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.wy.zp.bean.ImageMessage;
import com.wy.zp.bean.MusicMessage;
import com.wy.zp.bean.News;
import com.wy.zp.bean.NewsMessage;
import com.wy.zp.bean.TextMessage;
import com.wy.zp.bean.VideoMessage;
import com.wy.zp.bean.VoiceMessage;

public class XmlAndMap {
	
	//将request转map
    public static Map<String, String> xmlToMap(HttpServletRequest request){
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        try {
            InputStream ins = request.getInputStream();
             
            Document doc = reader.read(ins);
            Element root = doc.getRootElement();
             
            @SuppressWarnings("unchecked")
            List<Element> list = root.elements();
            for (Element e : list) {
                map.put(e.getName(), e.getText());
            }
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将文本回复对象转化为xml
     */
    public static String textMessageToXml(TextMessage testMessage){
        XStream xstream = new XStream();
        xstream.alias("xml",testMessage.getClass());
        return xstream.toXML(testMessage);
    }
    
    /**
     * 将图文回复转换为xml
     */
    public static String newsMessageToXml(NewsMessage newsMessage){
		XStream xstream = new XStream();
	
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new News().getClass());
//		xstream.aliasType("item", new News().getClass());
//		xstream.addImplicitCollection( newsMessage.getClass(), "Articles");
//		xstream.
		return xstream.toXML(newsMessage);

    }
    
    /**
     * 将图片信息转换为xml
     * @param newsMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage){
		XStream xstream = new XStream();
	
		xstream.alias("xml",imageMessage.getClass() );
		
		return xstream.toXML(imageMessage);

    }
    
    //将语音信息转换为xml
    public static String voiceMessageToXml(VoiceMessage voiceMessage){
		XStream xstream = new XStream();
		xstream.alias("xml",voiceMessage.getClass() );
		return xstream.toXML(voiceMessage);

    }
    
    //将视频信息转换为xml
    public static String videoMessageToXml(VideoMessage videoMessage){
		XStream xstream = new XStream();
		xstream.alias("xml",videoMessage.getClass() );
		return xstream.toXML(videoMessage);
    }
    
    //将音乐信息转换为xml
    public static String musicMessageToXml(MusicMessage musicMessage){
		XStream xstream = new XStream();
		xstream.alias("xml",musicMessage.getClass() );
		return xstream.toXML(musicMessage);
    }
    
}
