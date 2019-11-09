package ar.project.wclie.tests;

import java.net.MalformedURLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.project.ent.tools.jarLoader4Tests.SpringJUnitRunner;
import ar.project.wclie.remote.customer.Controller;
import ar.project.wclie.remote.customer.CustomerType;

@RunWith(SpringJUnitRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "txnManager", defaultRollback = false)
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CustomerServiceTest {
	
	@Autowired
	private Controller controller;
	
	@Test
	public void testController() throws MalformedURLException{
		List<CustomerType> list = controller.getAll();
		Assert.assertFalse(list.isEmpty());
	}

}
