package sj_web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {

	public static void main(String[] args) {
//		boolean a= new Boolean("0");
//		boolean b= new Boolean("1");
//		boolean c= new Boolean("true");
//		boolean d= new Boolean("false");
//		System.out.println(new Boolean("0").booleanValue());
//		System.out.println(new Boolean("1").booleanValue());
//		System.out.println(b);
//		System.out.println(c);
//		System.out.println(d);
//		new Boolean("").booleanValue()
		
		ApplicationContext factory=new ClassPathXmlApplicationContext("classpath:applicationContext.xml"); 
		System.out.println(factory.getBean("loginController"));
		
	}

}
