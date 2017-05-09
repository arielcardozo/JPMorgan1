package com.jpmg.core;

import java.time.*;
import java.time.format.*;
import java.util.*;

import org.junit.*;

public class MarketReportTest {

	@Test
	public void testDateFormat(){
		/*DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		String dates = "01 Jan 2016";
		// 2014-04-01 10:45
		LocalDateTime dateTime = LocalDateTime.of(2016, Month.JANUARY, 1, 10, 45);
		
		String now = "2016-11-09 10:30";
		String frenchDate = dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy", new Locale("en")));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMM yyyy", new Locale("en"));
        
        LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);
        LocalDateTime formatDateTime2 = LocalDateTime.parse(dates, formatter2);

        System.out.println("Before : " + frenchDate);

        System.out.println("After : " + formatDateTime);

        System.out.println("After : " + formatDateTime.format(formatter));*/
		
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
