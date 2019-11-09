package ar.project.web.actions.area.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.project.ent.entities.area.Persona;
import ar.project.ent.entities.area.PersonaExt;
import ar.project.ent.entities.area.User;
import ar.project.services.area.interfaces.PersonaService;
import ar.project.services.exceptions.ServException;
import ar.project.services.tools.UserProvider;

@Service("transactionalTest")
public class TransactionalPersonTestPart2 {
	
	@Autowired
	private PersonaService personaService;

	@Autowired
	private UserProvider userProvider;
	
	private String nombre = "TRX_nombre_TEST", apellido = "TRX_ape_TEST",
			direccion = "TRX_dir_TEST", telefono = "TRX_tel_TEST";
	
	
	// Poner todo en un test bien ver en snap globant
	@Transactional(rollbackFor = ServException.class)
	public void create() throws Exception {

		User user = userProvider.take();
		System.out.println(user.getUsername());
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

		if (true)
			throw new ServException();

	}

}
