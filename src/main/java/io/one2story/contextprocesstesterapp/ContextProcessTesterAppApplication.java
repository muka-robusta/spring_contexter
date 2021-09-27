package io.one2story.contextprocesstesterapp;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import io.one2story.contextprocesstesterapp.quoter.Quoter;
import io.one2story.contextprocesstesterapp.quoter.TerminatorQuoter;

@SpringBootApplication
public class ContextProcessTesterAppApplication {

	public static void main(String[] args) throws InterruptedException {
//		SpringApplication.run(ContextProcessTesterAppApplication.class, args);
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("context.xml");
		System.out.println(String.join(",", appContext.getBeanDefinitionNames()));
//		Method[] methods = appContext.getBean(Quoter.class).getClass().getMethods();
//		System.out.println("Methods -> " + Arrays.toString(methods));
//		while (true) {
//			Thread.sleep(1000);
//			System.out.println("======================");
//			
//		}
		appContext.getBean(Quoter.class).sayQuote();
		
	}

}
