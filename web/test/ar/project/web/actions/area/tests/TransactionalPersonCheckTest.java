package ar.project.web.actions.area.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.project.ent.entities.area.Persona;
import ar.project.ent.entities.area.User;
import ar.project.ent.tools.jarLoader4Tests.SpringJUnitRunner;
import ar.project.services.area.interfaces.PersonaService;
import ar.project.services.exceptions.ServException;
import ar.project.services.tools.UserProvider;

@RunWith(SpringJUnitRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "txnManager", defaultRollback=false)
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TransactionalPersonCheckTest {
	
		
	@Autowired
	private PersonaService personaService;

	@Autowired
	private UserProvider userProvider;
	
	@Test
	public void check() throws ServException{
		Assert.assertTrue(checkNoEntryWasSaved());
	}
	
	
	
	
    public boolean checkNoEntryWasSaved() throws ServException{
		
		User user = userProvider.take();
		System.out.println(user.getUsername());
		List<Persona> personas = personaService.getPersonas(user.getUsername());
		boolean found = false;
		for (Persona persona : personas) {
			if(persona.getApellido().equals("TRX_ape_TEST")){
				found = true;
				break;
			}
		}
		
		return !found;
	}

}
