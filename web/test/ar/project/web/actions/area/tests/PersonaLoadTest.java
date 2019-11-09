package ar.project.web.actions.area.tests;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.project.ent.entities.area.Persona;
import ar.project.ent.entities.area.PersonaExt;
import ar.project.ent.entities.area.User;
import ar.project.services.area.interfaces.PersonaService;
import ar.project.services.exceptions.ServException;
import ar.project.services.tools.UserProvider;

@Service("personaLoadTest")
public class PersonaLoadTest {
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private UserProvider userProvider;
	
	private SessionFactory sf;
	
	private int personasCreadasPorUsuario;
	
	private String nombre = "LOAD_nombre_TEST", apellido = "LOAD_ape_TEST",
			direccion = "LOAD_dir_TEST", telefono = "LOAD_tel_TEST";
	
	public User A_CreatePersonas() throws ServException{
		
		Session s1 = open();
        User user = userProvider.take();
        close(s1);
                
        for (int i = 0; i < personasCreadasPorUsuario; i++) {
        	
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
    		
    		Session session = open();
        	personaService.save(persona);
        	close(session);
        	
        	sleep(160);
		}
		return user;
		
	}
	
	public void B_UpdatePersonas(User user) throws ServException{
		Session s1 = open();
		List<Persona> personas = personaService.getPersonas(user.getUsername());
		close(s1);
		
		for (Persona persona : personas) {
			persona.setNombre(nombre + "_UPDATED");
			Session session = open();
			personaService.update(persona);
			close(session);
			
			sleep(30);
		}
		
	}
		
	public void C_DeletePersonas(User user) throws ServException{
		Session s1 = open();
		List<Persona> personas = personaService.getPersonas(user.getUsername());
		close(s1);
		
		for (Persona persona : personas) {
			Session session = open();
			personaService.delete(persona);
			close(session);
			
			sleep(10);
		}
	}
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	private Session open(){
		Session session = sf.openSession();
		ThreadLocalSessionContext.bind(session);
		return session;
	}
	
	private void close(Session session){
		ThreadLocalSessionContext.unbind(sf);
		session.close();
		
	}
	
	public int getPersonasCreadasPorUsuario() {
		return personasCreadasPorUsuario;
	}

	public void setPersonasCreadasPorUsuario(int personasCreadasPorUsuario) {
		this.personasCreadasPorUsuario = personasCreadasPorUsuario;
	}
	
	public void sleep(int centesimas) {

		for (int i = 0; i < centesimas; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
