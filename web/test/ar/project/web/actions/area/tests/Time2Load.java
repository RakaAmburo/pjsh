package ar.project.web.actions.area.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.project.ent.tools.jarLoader4Tests.SpringJUnitRunner;

@RunWith(SpringJUnitRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager = "txnManager", defaultRollback = false)
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class Time2Load {
	
	@Test
	public void doNothing(){
		
	}

}
