package com.wy.zp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wy.zp.bean.TextMessage;

//JSON、XML、MAP和对象的互转
public class TransFormUtil {
	
    private static XmlMapper xmlMapper = new XmlMapper();
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * 对象转map
     */
    public static Map<String, String> beanToMap(Object bean) {
        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        map.remove("class");
        return map;
    }
	
    
    

    /**
     * map转对象
     */
    public static <T> T mapToBean(Map<String, String> map, Class<T> class1) {
        T bean = null;
        try {
            bean = class1.newInstance();
            BeanUtils.populate(bean, map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
    
    /**
     * 对象转JSON
     */
    public static String beanToJson(Object bean) {
        String str = null;
        try {
            str = objectMapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;
    }
    
    /**
     * JSON转对象
     * @return
     */
    public static <T> T jsonToBean(String json, Class<T> class1) {
        T bean = null;
        try {
            bean = class1.newInstance();
            bean = objectMapper.readValue(json, class1);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bean;
    }
     
    /**
     * XML转JSON
     */
    public static String xmlToJson(String xml){
         
        StringWriter stringWriter = new StringWriter();  
        JsonParser jsonParser;
        try {
            jsonParser = xmlMapper.getFactory().createParser(xml);
            JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(stringWriter);  
            while (jsonParser.nextToken() != null) {  
                jsonGenerator.copyCurrentEvent(jsonParser);  
            }  
            jsonParser.close();  
            jsonGenerator.close();
        } catch (Exception e) {
            e.printStackTrace();
        }  
          
        return stringWriter.toString();  
    }
    
    /**
     * JSON转XML
     */
    public static String jsonToXml(String json){
        try {
            JsonNode root = objectMapper.readTree(json);  
            String xml = xmlMapper.writeValueAsString(root);  
            return xml;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 对象转XML
     */
    public static String beanToXml(Object bean) {
        XStream xStream  = initXStream();
        xStream.alias("xml", bean.getClass());
        return xStream.toXML(bean);  
    }
    
    /**
     * XML转对象
     */
    public static Object xmlToBean(String xml, Object bean) {
         
        XStream xStream = initXStream();
        xStream.alias("xml", bean.getClass());
        return xStream.fromXML(xml);
    }
    
    /**
     * 输入流转对象
     */
    public static Object inputStreamToBean(InputStream in, Object bean) {
        XStream xStream = initXStream();
        xStream.alias("xml", bean.getClass());
        return xStream.fromXML(in);
    }
    
    private static XStream initXStream() {
        return new XStream(new XppDriver() {
            @Override
            public HierarchicalStreamReader createReader(Reader in) {
                 
                return super.createReader(in);
            }
 
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) { 
                     
                     @Override
                     public String encodeNode(String name) {
                         return name;
                     }
                    protected void writeText(QuickWriter writer, String text) {
                        writer.write("<![CDATA[" + text + "]]>");
                    }
                };
            }
        });
    }
    
    
    

    
    
    
    
    
}
