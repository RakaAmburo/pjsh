package ar.project.persist.dynConfigs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Iterator;

import org.hibernate.mapping.PersistentClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
public class DynamicConfigurations {
	
	@Autowired
	private LocalSessionFactoryBean sessionFactory;
    
    @Bean(name = "sqliteDynDataSrc")
    public static DriverManagerDataSource sqliteDynDataSrc(){
    	
    	URL url = DynamicConfigurations.class.getClassLoader().getResource("base2.db");
		
    	
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	dataSource.setDriverClassName("org.sqlite.JDBC");
    	dataSource.setUrl("jdbc:sqlite:" + url.getPath());
    	
    	return dataSource;
    	
    }    
    
    @Bean(name = "entCustomAnnotations")
    @DependsOn(value="sessionFactory")
    public Object entCustomAnnotations() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    	
    	Iterator<PersistentClass> entities =sessionFactory.getConfiguration().getClassMappings();
    	while(entities.hasNext()) {
    		PersistentClass entity = entities.next();
    		Class<?> clazz = entity.getMappedClass();
    		Method m = clazz.getSuperclass().getDeclaredMethod("InjectColumnName", Class.class);
    		m.setAccessible(Boolean.TRUE);
    		m.invoke(null, clazz);
            
         }  	
    	
		return null;
    	
    }
    
}
