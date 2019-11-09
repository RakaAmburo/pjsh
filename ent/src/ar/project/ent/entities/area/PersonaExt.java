package ar.project.ent.entities.area;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.project.ent.base.AbstractEntity;

@Entity
@Table(name = "persona_ext")
public class PersonaExt extends AbstractEntity<Integer>{
	
	private Integer id;
	private String codigoPostal;
	private Integer edad;
	
	@Id
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="cd_postal", length=45)
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	@Column(name="edad", length=45)
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
	

}
