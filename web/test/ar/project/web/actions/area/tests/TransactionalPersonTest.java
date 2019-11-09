package ar.project.web.actions.area.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import ar.project.ent.tools.jarLoader4Tests.SpringJUnitRunner;
import ar.project.services.exceptions.ServException;

@RunWith(SpringJUnitRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "txnManager", defaultRollback=true)
public class TransactionalPersonTest {

	@Autowired
	TransactionalPersonTestPart2 test;	

	@Test
	public void create() throws ServException {

		try {
			test.create();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
}
