package com.bddframework.utils;

import java.util.Date;

public class EmailDate {
	
	public static String emailWithDateTimeStamp() {
		Date date = new Date();
		
		System.out.println(date); 
		String emailTimeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "MamdOsma" + emailTimeStamp + "@gmail.com";
	}

}
