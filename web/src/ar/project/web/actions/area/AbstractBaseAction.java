package ar.project.web.actions.area;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.project.ent.entities.area.Language;
import ar.project.ent.entities.area.PersonTag;
import ar.project.ent.entities.area.Persona;
import ar.project.ent.entities.area.PersonaExt;

import com.opensymphony.xwork2.ActionSupport;



public abstract class AbstractBaseAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	private Integer paisId;
	private PersonaExt personaExt;
	private String userName;
	private List<String> languages;
	private List<String> tags;
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		persona.setUsername(userName);
	}

	//So I can fill persona just one time
	private Persona persona = new Persona();

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		persona.setNombre(nombre);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		persona.setId(id);
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
		persona.setApellido(apellido);
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
		persona.setDireccion(direccion);
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
		persona.setTelefono(telefono);
	}
	
	
	//Return already filled persona
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public Integer getPaisId() {
		return paisId;
	}

	public void setPaisId(Integer paisId) {
		this.paisId = paisId;
		persona.setPaisId(paisId);
	}
	
	public PersonaExt getPersonaExt() {
		return personaExt;
	}

	public void setPersonaExt(PersonaExt personaExt) {
		this.personaExt = personaExt;
		persona.setPersonaExt(personaExt);
	}
	
	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
		Set<Language> langList = new HashSet<Language>();
		for (String id : languages) {
			Language lang = new Language();
			lang.setId(Integer.parseInt(id));
			langList.add(lang);
		}
		persona.setLanguages(langList);
	}
	
	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
		Set<PersonTag> tagList = new HashSet<PersonTag>();
		for (String tag : tags) {
			PersonTag ptag = new PersonTag();
			ptag.setTag(tag);
			tagList.add(ptag);
		}
		persona.setTags(tagList);
	}

}
