package ar.project.web.actions.area.tests;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
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
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "txnManager", defaultRollback = false)
@Transactional(propagation = Propagation.REQUIRES_NEW)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoadTest {
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private UserProvider userProvider;
	
	private String nombre = "LOAD_nombre_TEST", apellido = "LOAD_ape_TEST",
			direccion = "LOAD_dir_TEST", telefono = "LOAD_tel_TEST";
	
	private static String userName;
	
	@Test
	public void A_CreatePersonas() throws ServException{
		
        User user = userProvider.take();
        userName = user.getUsername();
        
        for (int i = 0; i < 100; i++) {
        	
        	Persona persona = new Persona();
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
		}
		
	}
	
	@Test
	public void B_UpdatePersonas() throws ServException{
		List<Persona> personas = personaService.getPersonas(userName);
		System.out.println("PERSONAS A UPDETEAR: " + personas.size());
		
		for (Persona persona : personas) {
			persona.setNombre(nombre + "_UPDATED");
			personaService.update(persona);
		}
		
	}
		
	@Test
	public void C_DeletePersonas() throws ServException{
		List<Persona> personas = personaService.getPersonas(userName);
		System.out.println("PERSONAS A BORRAR: " + personas.size());
		
		for (Persona persona : personas) {
			personaService.delete(persona);
		}
	}

}


//RESULTS (create,update,delete 100 users):
//mysql srpingdriver 0.897 0.319 0.234
//mysql srpingdriver 0.969 0.285 0.231
//mysql srpingdriver 0.895 0.283 0.226

//mysql c3p0 0.968 0.309 0.207
//mysql c3p0 0.921 0.285 0.200
//mysql c3p0 0.894 0.286 0.301

//mysql bone 0.924 0.301 0.193
//mysql bone 0.891 0.292 0.232
//mysql bone 0.882 0.280 0.187

//posgre sprigngdriver 1.082 0.315 0.251
//posgre sprigngdriver 1.055 0.348 0.268
//posgre sprigngdriver 0.983 0.349 0.243

//posgre c3p0 1.028 0.341 0.251
//posgre c3p0 1.029 0.324 0.276
//posgre c3p0 0.978 0.332 0.267

//posgre bone 0.938 0.266 0.209
//posgre bone 0.890 0.283 0.200
//posgre bone 0.899 0.274 0.184


//oracle srpingdriver 1.410 0.459 0.384
//oracle srpingdriver 1.315 0.407 0.360
//oracle srpingdriver 1.344 0.317 0.393

//oracle c3p0 1.297 0.358 0.367
//oracle c3p0 1.354 0.342 0.344
//oracle c3p0 1.329 0.334 0.335

//oracle bone 1.185 0.326 0.317
//oracle bone 1.187 0.317 0.310
//oracle bone 1.180 0.307 0.343

//sqlite 0.696 0.500 0.414
//sqlite 0.776 0.495 0.354
//sqlite 0.730 0.510 0.226
