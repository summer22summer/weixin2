package com.wy.zp.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TestSet {

	public static void main(String[] args) {
		
		  
//	       TreeSet ts=new TreeSet();
//	       ts.add("orange");
//	       ts.add("apple");
//	       ts.add("banana");
//	       ts.add("grape");
//	       Iterator it=ts.iterator();
//	       while(it.hasNext())
//	       {
//	           String fruit=(String)it.next();
//	           
//	           System.out.println(fruit);
//	       }
	       
		Map<String,Object> map = new HashMap<String,Object>();
	    
		map.put("1", 1);
		map.put("1", 2);
		
		for(Map.Entry<String, Object> entry :map.entrySet()){
			System.out.println(   entry.getValue()+"*****"+entry.getKey()  );
		}
		

		
		

	}
	

	
	

}
