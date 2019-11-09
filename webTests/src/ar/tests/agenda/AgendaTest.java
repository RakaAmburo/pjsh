package ar.tests.agenda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.project.ent.entities.area.User;
import ar.project.ent.tools.jarLoader4Tests.SpringJUnitRunner;
import ar.project.services.tools.UserProvider;

@RunWith(SpringJUnitRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "txnManager", defaultRollback = false)
@Transactional(propagation = Propagation.REQUIRES_NEW)
public abstract class AgendaTest {
	
	  public WebDriver driver;
	  
	  @Autowired
	  private UserProvider userProvider;
	  
	  public String userName;
	  
	  @Test
	  @Repeat(value = 1)
	  public void testAgenda() throws Exception {
		  
		User user = userProvider.take();
		userName = user.getUsername();
		
		AgendaInstructions instructions = new AgendaInstructions();
		
		instructions.executeInstructions(driver, userName, false);

        
	  }
	  
	  

}
