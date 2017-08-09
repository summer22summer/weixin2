package com.wy.zp.test;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class PCTest {

	public static void main(String[] args) {
		
		 City city1 = new City();
		 city1.setName("北京");
		 city1.setDescription("北京啊");
		
		 City city2 = new City();
		 city2.setName("北666京");
		 city2.setDescription("北666京啊");
		 
		 List<City> cities = new ArrayList<City>();
		 cities.add(city1);
		 cities.add(city2);
		 
		 Province p = new Province(); 
		 p.setName("上海呀");
		 p.setCities(cities);
		 
		 

		


		
		XStream xstream = new XStream();  


        xstream.alias("Province", Province.class);   
        xstream.alias("city", City.class); 
		String s = xstream.toXML(p);  
		System.out.println( s );

	}

}
