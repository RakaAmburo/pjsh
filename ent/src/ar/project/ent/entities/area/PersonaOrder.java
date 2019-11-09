package ar.project.ent.entities.area;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.project.ent.annotations.ColumnName;
import ar.project.ent.base.AbstractEntity;

@Entity
@Table(name = "persona_orden")
public class PersonaOrder extends AbstractEntity<Integer>{
	
	private Integer id;
	private Integer personaId;
	private Integer orden;
	private String usuario;
	
	@ColumnName
	public static final String USUARIO = new String();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@SequenceGenerator(name = "porden_sequence", sequenceName = "porden_sequence")
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "porden_sequence")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="persona_id")
	public Integer getPersonaId() {
		return personaId;
	}
	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}
	
	@Column(name="persona_orden")
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
	@Column(name="usuario", length=45)
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
