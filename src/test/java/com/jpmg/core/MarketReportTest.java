package com.jpmg.core;

import java.time.*;
import java.time.format.*;
import java.util.*;

import org.junit.*;

public class MarketReportTest {

	@Test
	public void testDateFormat(){
		
		
		 MonthDay m;
	        Locale.setDefault(Locale.ENGLISH);
	        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd MMM yyyy");
	        String dateString = "12 Jan 1900";

	        try
	        {
	            LocalDate ddd = LocalDate.parse(dateString,dTF);
	            System.out.println(ddd.toString());
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
		
		
		
	}
}
