package sj_web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test1 {

	public static void main(String[] args) {
		ApplicationContext factory=new ClassPathXmlApplicationContext("classpath:applicationContext.xml"); 
		System.out.println(factory.getBean("loginController"));

	}

}
