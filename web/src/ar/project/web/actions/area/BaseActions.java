package ar.project.web.actions.area;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;

import ar.project.ent.entities.area.Language;
import ar.project.ent.entities.area.PersonTag;
import ar.project.ent.entities.area.Persona;
import ar.project.services.area.interfaces.PersonaService;
import ar.project.services.exceptions.ServException;

@Namespace("/")
@ResultPath(value = "/")
@ParentPackage("json-default")
public class BaseActions extends AbstractBaseAction implements SessionAware {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PersonaService personaService;
	
	@Value("${clave.secreta}")
	private String clave;
	
	//@Autowired
	//private MessageSource messageSource;

	private HttpServletRequest request = ServletActionContext.getRequest();
	private static final Logger LOGGER = Logger.getLogger(BaseActions.class);
	private boolean status;
	private List<Persona> personas;
	private Map<Integer, String> ctryMap;
	private String countryLike;
	private Integer returnedId;
	private String loginError;
	private Map<Integer, String> langMap;
	private List<Language> langList;
	
	private String userName = SecurityContextHolder.getContext().getAuthentication().getName();
	
	private String nameUser = userName;
  
	@Action(value = "login", results = {
			@Result(name = SUCCESS, location = "/jsp/login/form.jsp"),
			@Result(name = ERROR, location = "/jsp/login/error.jsp")})
	public String login() {
        
		LOGGER.debug(clave);
		if (loginError != null && loginError.equals("true")){
			return ERROR;
		} 
		LOGGER.debug("Rendering login screen");
		return SUCCESS;
	}

	@Action(value = "home", results = {
			@Result(name = SUCCESS, location = "/jsp/area/personas.jsp"),
			@Result(name = ERROR, location = "/jsp/area/error.jsp") })
	public String execute() {
		
		LOGGER.debug("pedimos perosnas a servicios");

        try {
        	setPersonas(personaService.getPersonas(userName));
        	LOGGER.info("obtenemos persons");
        	langList = personaService.getLangList();        	
        	
		} catch (Exception e) {
			LOGGER.error(e);
			return ERROR;
		}
		

		return SUCCESS;
	}
	

	@Action(value = "saveOrder", results = { @Result(name = SUCCESS, type = "json", params = {
			"includeProperties", "status" }) })
	public String saveOrder() {
        
		LOGGER.debug("Sorting list of persons");
		String[] jsonOrder = request.getParameterValues("jsonOrder[]");
		
		try {
			personaService.saveOrder(jsonOrder, userName);
			status = true;
			return SUCCESS;
		} catch (Exception e) {
			status = false;
			return SUCCESS;
			
		}
		
	}
	

	@Action(value = "savePerson", results = { @Result(name = SUCCESS, type = "json", params = {
			"includeProperties", "status, returnedId" }) })
	public String savePerson() {

				
		status = true;
		setUserName(userName);
		
		if (getId() == null) {
			LOGGER.debug("SAVING person");
			try {
				returnedId = personaService.save(getPersona());
			} catch (ServException e) {
				status = false;
				return SUCCESS;
			}
		} else {
			try {
				LOGGER.debug("UPDATING person");
				personaService.update(getPersona());
			} catch (Exception e) {
				LOGGER.error(e);
				status = false;
			}
		}
		LOGGER.info("Persona id "+getPersona().getId());

		return SUCCESS;
	}
	

	@Action(value = "deletePerson", results = { @Result(name = SUCCESS, type = "json", params = {
			"includeProperties", "status, returnedId" }) })
	public String deletePerson() {
		setUserName(userName);
		LOGGER.debug("DELETING person");
		LOGGER.info(getPersona().getId());
		try {
			personaService.delete(getPersona());
			status = true;
			return SUCCESS;
		} catch (ServException e) {
			LOGGER.error("Error borrando: ",e);
			status = false;
			return SUCCESS;
		}

		
	}

	@Action(value = "languages", results = { @Result(name = SUCCESS, type = "json", params = {
			"includeProperties", "langMap.*" }) })
	public String readLanguages(){
		
		try {
			langMap = personaService.getLanguages();
		} catch (ServException e) {
			LOGGER.error("Error obeniendo lenguajes (hay que manejar este error)");
		}
		
		return SUCCESS;
	}
	
	@Action(value = "countries", results = { @Result(name = SUCCESS, type = "json", params = {
			"includeProperties", "ctryMap.*" }) })
	public String readCountries() {
        LOGGER.debug("Returning country json");
		try {
			ctryMap = personaService.getPaises(countryLike);
		} catch (ServException e) {
			LOGGER.error("Error obeniendo paises (hay que manejar este error)");
		}

		return SUCCESS;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		if(personas.isEmpty()){
			Persona p = new Persona();
			p.setTags(new HashSet<PersonTag>());
			p.setOrden(1);
			personas.add(p);
		}
		this.personas = personas;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Map<Integer, String> getCtryMap() {
		return ctryMap;
	}

	public void setCtryMap(Map<Integer, String> ctryMap) {
		this.ctryMap = ctryMap;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO ?????

	}

	public String getCountryLike() {
		return countryLike;
	}

	public void setCountryLike(String countryLike) {
		this.countryLike = countryLike;
	}


	public Integer getReturnedId() {
		return returnedId;
	}

	public void setReturnedId(Integer returnedId) {
		this.returnedId = returnedId;
	}
	
	public String getLoginError() {
		return loginError;
	}

	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	
	public Map<Integer, String> getLangMap() {
		return langMap;
	}

	public void setLangMap(Map<Integer, String> langMap) {
		this.langMap = langMap;
	}
	
	public List<Language> getLangList() {
		return langList;
	}

	public void setLangList(List<Language> langList) {
		this.langList = langList;
	}

}
