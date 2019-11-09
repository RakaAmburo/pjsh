package ar.project.webServ.area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import ar.project.ent.entities.area.Persona;
import ar.project.services.area.interfaces.PersonaService;
import ar.project.services.exceptions.ServException;

@WebService(endpointInterface="ar.project.webServ.area.MathModule")
public class MathModuleImpl implements MathModule {

	@Autowired
	private PersonaService personaService;
	
	private static final Logger LOGGER = Logger
			.getLogger(MathModuleImpl.class);

	@WebMethod
	public boolean isOdd(Integer number) {
		if (number == null) {
			return false;
		}
		if (number.intValue() % 2 == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	@WebMethod
	public Map<Integer, String> paises(){
		
		Map<Integer, String> ispas = new HashMap<Integer, String>();
		try {
			ispas = personaService.getPaises("a");
		} catch (ServException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ispas;
		
		
	}
	
	@WebMethod
	public List<Persona4Ws> getPersonas(){
		LOGGER.debug("Nos piden personas de un cliente");
		LOGGER.info("WEBSERVICE - PERSONAS");
		List<Persona> personas = new ArrayList<Persona>();
		try {
			personas = personaService.getPersonas("pablo");
		} catch (ServException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Persona4Ws> p4wsList = new ArrayList<Persona4Ws>();
		for (Persona persona : personas) {
			p4wsList.add(new Persona4Ws(persona));
		}
		
		return p4wsList;
	}
	
}
