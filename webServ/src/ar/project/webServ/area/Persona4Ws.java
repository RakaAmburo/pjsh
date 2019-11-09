package ar.project.webServ.area;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import ar.project.ent.entities.area.Persona;

@Entity
@XmlRootElement
public class Persona4Ws {

	public Persona4Ws() {
		super();
	}

	public Persona4Ws(Persona persona) {
		this.id = persona.getId();
		this.nombre = persona.getNombre();
		this.apellido = persona.getApellido();
		this.direccion = persona.getDireccion();
		this.telefono = persona.getTelefono();
		this.orden = persona.getOrden();
		this.country = persona.getCountry().getName();
		this.username = persona.getUsername();
	}

	private Integer id;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	private Integer orden;
	private String country;
	private String username;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
}
