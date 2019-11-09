package ar.project.mvc.controllers.area;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.project.ent.entities.area.PersonTag;
import ar.project.ent.entities.area.Persona;
import ar.project.ent.entities.area.PersonaExt;
import ar.project.services.area.interfaces.PersonaService;
import ar.project.services.exceptions.ServException;

@Controller
public class HomeController  {
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private MessageSource messageSource;
	
	private static final Logger LOGGER = Logger.getLogger(HomeController.class);
	
	@RequestMapping("/personas")
	public ModelAndView getPersonas(){
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		ModelAndView model = new ModelAndView("personas", "nameUser", userName);
		
		Locale locale = LocaleContextHolder.getLocale();
		String hOrden = messageSource.getMessage("headers.nombre", null, locale);
		String hNombre = messageSource.getMessage("headers.orden", null, locale);
		String hApellido = messageSource.getMessage("headers.apellido", null, locale);
		String hDir = messageSource.getMessage("headers.direccion", null, locale);
		String hTel = messageSource.getMessage("headers.telefono", null, locale);
		String hPais = messageSource.getMessage("headers.pais", null, locale);
		String hEdad = messageSource.getMessage("headers.edad", null, locale);
		String hCPost = messageSource.getMessage("headers.cpostal", null, locale);
		String hMore = messageSource.getMessage("headers.more", null, locale);
		String hSavDel = messageSource.getMessage("headers.saveDel", null, locale);
		String hAdd = messageSource.getMessage("headers.add", null, locale);
		String hMove = messageSource.getMessage("headers.move", null, locale);
		
		model.addObject("hOrden", hOrden);
		model.addObject("hNombre", hNombre);
		model.addObject("hApellido", hApellido);
		model.addObject("hDir", hDir);
		model.addObject("hTel", hTel);
		model.addObject("hPais", hPais);
		model.addObject("hEdad", hEdad);
		model.addObject("hCPost", hCPost);
		model.addObject("hMore", hMore);
		model.addObject("hSavDel", hSavDel);
		model.addObject("hAdd", hAdd);
		model.addObject("hMove", hMove);
		
		LOGGER.info("getPersonas");
		LOGGER.debug("Informacion necesaria getting persons");
		List<Persona> personas;
		try {
			personas = personaService.getPersonas(userName);
			if(personas.isEmpty()){
				Persona p = new Persona();
				p.setTags(new HashSet<PersonTag>());
				p.setOrden(1);
				personas.add(p);
			}
						
			model.addObject("personas", personas);
		} catch (ServException e) {
			LOGGER.error("ver como resolver un error en mvc");
		}
		
		
		
		return model;
	}

	@RequestMapping("/home")
	public ModelAndView getHome() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Persona> personas;
		int size = 0;
		try {
			personas = personaService.getPersonas(userName);
			size = personas.size();
		} catch (ServException e) {
			LOGGER.error("not working");
		}
		
		Map<Integer, String> paises = null;
		try {
			paises = personaService.getPaises("a");
		} catch (ServException e) {
			LOGGER.error("not working");
		}
		
		String string = userName + " Congrats ! You are done with your first Spring Security configuration ! " + size;
		
		ModelAndView model = new ModelAndView("home", "string", string);
		
		model.addObject("paises", paises);
		
		return model;
	}
	
	@RequestMapping(value="/countries", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String getAllProjects(@RequestParam String countryLike) throws JSONException {
		
		Map<Integer, String> ctryMap = null;
		try {
			ctryMap = personaService.getPaises(countryLike);
		} catch (ServException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		Map<String, Map<Integer, String>> bigMap = new HashMap<String, Map<Integer, String>>();
		
		bigMap.put("ctryMap", ctryMap);

	    JSONObject jsonItem = new JSONObject(bigMap);
	    

	    return jsonItem.toString();
	}
	
	@RequestMapping(value="/savePerson", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String savePerson(@RequestParam Integer id,
			                 @RequestParam String nombre,
			                 @RequestParam String apellido,
			                 @RequestParam String direccion,
			                 @RequestParam String telefono,
			                 @RequestParam Integer paisId,
			                 @RequestParam (value = "personaExt.edad", required = false) Integer personaExt_edad,
			                 @RequestParam (value = "personaExt.codigoPostal", required = false) String personaExt_codigoPostal,
			                 @RequestParam (value = "personaExt.id", required = false) Integer personaExt_id) throws JSONException {// ver si puedo pasar el objeto directamente

		//LOGGER.info("el id del pais que se va a cambiar");
		//LOGGER.info(getPaisId());
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean status = true;
		Persona persona = new Persona();
		Integer returnedId = null;
		
		persona.setNombre(nombre);
		persona.setApellido(apellido);
		persona.setDireccion(direccion);
		persona.setTelefono(telefono);
		persona.setPaisId(paisId);
		PersonaExt pext = new PersonaExt();
		pext.setId(personaExt_id);
		pext.setEdad(personaExt_edad);
		pext.setCodigoPostal(personaExt_codigoPostal);
		persona.setPersonaExt(pext);
		persona.setUsername(userName);
		persona.setTags(new HashSet<PersonTag>());//harcodeado arreglar
		
		if (id == null) {	
			try {
				returnedId = personaService.save(persona);
			} catch (ServException e) {
				status = false;
			}
		} else {
			try {
				persona.setId(id);
				returnedId = id;
				personaService.update(persona);
			} catch (Exception e) {
				LOGGER.error(e);
				status = false;
			}
		}
		
		JSONObject jsonItem = new JSONObject();
		jsonItem.put("status", status);
		jsonItem.put("returnedId", returnedId);
		
		return jsonItem.toString();
	}
	
	@RequestMapping(value="/saveOrder", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String saveOrder(@RequestParam(value = "jsonOrder[]", required = false) String[] jsonOrder) throws JSONException {

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean status = true;
		try {
			personaService.saveOrder(jsonOrder, userName);
		} catch (ServException e) {
			status = false;
		}
		JSONObject jsonItem = new JSONObject();
		jsonItem.put("status", status);
		
		return jsonItem.toString();
	}
	
	
	@RequestMapping(value="/deletePerson", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String deletePerson(@RequestParam Integer id,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String direccion,
            @RequestParam String telefono,
            @RequestParam Integer paisId,
            @RequestParam (value = "personaExt.edad", required = false) Integer personaExt_edad,
            @RequestParam (value = "personaExt.codigoPostal", required = false) String personaExt_codigoPostal,
            @RequestParam (value = "personaExt.id", required = false) Integer personaExt_id) throws JSONException {
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean status = true;
		
		Persona persona = new Persona();
		
		persona.setId(id);
		persona.setNombre(nombre);
		persona.setApellido(apellido);
		persona.setDireccion(direccion);
		persona.setTelefono(telefono);
		persona.setPaisId(paisId);
		PersonaExt pext = new PersonaExt();
		pext.setId(personaExt_id);
		pext.setEdad(personaExt_edad);
		pext.setCodigoPostal(personaExt_codigoPostal);
		persona.setPersonaExt(pext);
		persona.setUsername(userName);
		persona.setTags(new HashSet<PersonTag>());//harcodeado arreglar
		
		try {
			personaService.delete(persona);
		} catch (ServException e) {
			LOGGER.error(e);
			status = false;
		}

		JSONObject jsonItem = new JSONObject();
		jsonItem.put("status", status);
		
		return jsonItem.toString();
	}

}
