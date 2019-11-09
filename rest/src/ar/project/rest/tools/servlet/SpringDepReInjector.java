package ar.project.rest.tools.servlet;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.springframework.context.ApplicationContext;

import ar.project.rest.services.area.Hello;
import ar.project.rest.tools.annotations.ReInject;
import ar.project.rest.tools.appCtxt.ApplicationContextProvider;

public class SpringDepReInjector {
	
	public static void main(String[] args) {
		parse2(Hello.class);
	}
	
	public static void parse(Object obj){
		
		ApplicationContext ac = ApplicationContextProvider.getApplicationContext();
		
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			ReInject annotation = field.getAnnotation(ReInject.class);
			if (annotation != null) {
				field.setAccessible(true);
				try {
					//System.out.println(field.getType().getClass());
					field.set(obj, ac.getBean(Introspector.decapitalize(field.getType().getSimpleName())));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				field.setAccessible(false);
			}
		}
	    
		
	}
	
public static void parse2(Class<?> clazz){
		
		ApplicationContext ac = ApplicationContextProvider.getApplicationContext();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			
			ReInject annotation = field.getAnnotation(ReInject.class);
			if (annotation != null) {
				if (!Modifier.isStatic(field.getModifiers())){
					System.err.println("ReInject annotation fields must be static: " + clazz.getName() + " " + field.getName());
					System.exit(1);
				}
				
				field.setAccessible(true);
				try {
					//field.set(null, null);
					field.set(null, ac.getBean(Introspector.decapitalize(field.getType().getSimpleName())));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				field.setAccessible(false);
			}
		}
	    
		
	}

}
