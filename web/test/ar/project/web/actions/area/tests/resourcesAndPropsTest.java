package ar.project.web.actions.area.tests;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.project.ent.tools.jarLoader4Tests.SpringJUnitRunner;

@RunWith(SpringJUnitRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager = "txnManager", defaultRollback = false)
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class resourcesAndPropsTest {
	
	@Value("${probando}")
	private String propiedad;
	
	@Autowired
	private MessageSource messageSource;
	
	@Test
	public void soNothing(){}
	
	@Test
	public void resources(){
		
		Locale locale = LocaleContextHolder.getLocale();
		String local = messageSource.getMessage("personas.test", null, locale);
		Locale difLocale = new Locale("fr", "FR");
		//ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", difLocale);
		String frances = messageSource.getMessage("personas.test", null, difLocale);
		
		Assert.assertEquals("PROBANDESCO",local);
		Assert.assertEquals("probandu",frances);
	
	}
	
	public void properties(){
		
		Assert.assertEquals("testeando", propiedad);
		
	}

}
