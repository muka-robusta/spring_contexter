package io.one2story.contextprocesstesterapp.quoter;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private ConfigurableListableBeanFactory factory;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext appContext = event.getApplicationContext();
		String[] names = appContext.getBeanDefinitionNames();
		for (String name : names) {
			BeanDefinition beanDefinition = factory.getBeanDefinition(name);
			String originalClassName = beanDefinition.getBeanClassName();
			try {
				Class<?> originalClass = Class.forName(originalClassName);
				Method[] methods = originalClass.getMethods();
				for (Method method : methods) {
					if (method.isAnnotationPresent(PostProxy.class)) {
						Object bean = appContext.getBean(name);
						Method currentMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
						currentMethod.invoke(bean);
					}
						
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
