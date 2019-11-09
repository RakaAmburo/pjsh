package ar.project.rest.services.area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import ar.project.ent.entities.area.Persona;
import ar.project.rest.tools.annotations.ReInject;
import ar.project.rest.tools.servlet.JerseyServlet;
import ar.project.services.area.interfaces.PersonaService;
import ar.project.services.exceptions.ServException;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/hello")
public class Hello {

	@ReInject
	private static PersonaService personaService;
	
	@ReInject
	private static MessageSource messageSource;
	
	private static final Logger LOGGER = Logger
			.getLogger(Hello.class);

	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Todo getXML() {
		LOGGER.debug("Devolvemos todo como xml en post");
		Todo todo = new Todo();
		Locale locale = LocaleContextHolder.getLocale();
		String mess = messageSource.getMessage("personas.test", null, locale);
		todo.setSummary("MENSAJE " + mess);
		todo.setDescription("This is my first todo");
		return todo;
	}

	// This can be used to test the integration with the browser
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Persona> getHTML() {
		LOGGER.debug("Devolvemos personas como json en get");
		LOGGER.info("Get - personas by rest");
		Map<Integer, String> paises = new HashMap<Integer, String>();
		try {
			paises = personaService.getPaises("ar");
		} catch (ServException e) {
			e.printStackTrace();
		}
		Todo todo = new Todo();
		todo.setSummary(paises.values().toString());

		List<Persona> personas = new ArrayList<Persona>();
		try {
			personas = personaService.getPersonas("pablo");
		} catch (ServException e) {
			e.printStackTrace();
		}
		todo.setDescription("This is my first todo " + JerseyServlet.PACKAGES);
		ArrayList<Todo> lista = new ArrayList<Todo>();
		lista.add(todo);
		lista.add(todo);
		return personas;
	}

	@Path("{container}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Todo getContainerResource(@PathParam("container") String container) {
		LOGGER.debug("Devolvemos todo como json en get con parametro");
		Todo todo = new Todo();
		todo.setSummary("This is my first todo");
		todo.setDescription(container);
		return todo;
	}
	
	@Path("{letras}")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public Map<Integer, String> getPaises(@PathParam("letras") String letras){
		LOGGER.debug("Devolvemos paises como json en post con parametro");
		Map<Integer, String> paises = new HashMap<>();
		try {
			paises = personaService.getPaises(letras);
		} catch (ServException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return paises;
		
	}

}
