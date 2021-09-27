package io.one2story.contextprocesstesterapp.quoter;

import java.lang.reflect.Field;
import java.util.Random;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) {
		Field[] declaredFields = bean.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
			if (annotation != null) { // annotation is set
				int min = annotation.min();
				int max = annotation.max();
				Random random = new Random();
				int i = min + random.nextInt(max - min);
				field.setAccessible(true);
				ReflectionUtils.setField(field, bean, i);
			}
		}
 		return bean;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
	
		return bean;
	}
	
}
