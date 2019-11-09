package ar.project.web.actions.area.tests;

import java.util.Locale;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.project.ent.entities.area.Persona;
import ar.project.ent.entities.area.PersonaExt;
import ar.project.ent.entities.area.User;
import ar.project.ent.tools.jarLoader4Tests.SpringJUnitRunner;
import ar.project.services.area.interfaces.PersonaService;
import ar.project.services.exceptions.ServException;
import ar.project.services.tools.UserProvider;


@RunWith(SpringJUnitRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@FixMethodOrder(MethodSorters.DEFAULT)
@TransactionConfiguration(transactionManager = "txnManager", defaultRollback = false)
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class BaseActionsTest {
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private UserProvider userProvider;
	
	@Value("${probando}")
	private String propiedad;
	
	@Autowired
	private MessageSource messageSource;
	
	private String nombre = "TEST_nombre_TEST", apellido = "TEST_ape_TEST",
			direccion = "TEST_dir_TEST", telefono = "TEST_tel_TEST";
	
	private static Persona persona;
	
	@Test
	public void actionOne() throws ServException{
		
		User user = userProvider.take();
		
		persona = new Persona();
		persona.setNombre(nombre);
		persona.setApellido(apellido);
		persona.setDireccion(direccion);
		persona.setTelefono(telefono);
		persona.setUsername(user.getUsername());
		PersonaExt pe = new PersonaExt();
		pe.setCodigoPostal("111");
		pe.setEdad(23);
		persona.setPersonaExt(pe);
		personaService.save(persona);
		
		Persona p = personaService.getPersonas(user.getUsername()).get(0);
		String nombre = p.getNombre();
		Assert.assertNotNull(nombre);
		
				
	}
	
	@Test
	public void actionTwo() throws ServException{
		Persona p2del = personaService.getPersona(persona.getId());
		personaService.delete(p2del);
		Persona p = personaService.getPersona(persona.getId());
		Assert.assertNull(p);
	}
	
	@Test
	public void testResourcesYProps(){
		Locale locale = LocaleContextHolder.getLocale();
		String local = messageSource.getMessage("personas.test", null, locale);
		Assert.assertNotNull(propiedad);
		Assert.assertNotNull(local);
	}

}
