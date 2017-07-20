package sj_web;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class test1 {

	public static void main(String[] args) {
		// ApplicationContext factory=new
		// ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		// System.out.println(factory.getBean("loginController"));

		LocalDateTime arrivalDate = LocalDateTime.now();
		try {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String landing = arrivalDate.format(format);
			System.out.printf("Arriving at :  %s %n", landing);
		} catch (DateTimeException ex) {
			System.out.printf("%s can't be formatted!%n", arrivalDate);
			ex.printStackTrace();
		}
		
		  Date currentTime = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String dateString = formatter.format(currentTime);
		  ParsePosition pos = new ParsePosition(8);
		  Date currentTime_2 = formatter.parse(dateString, pos);
		  System.out.println(dateString);

	}

}
