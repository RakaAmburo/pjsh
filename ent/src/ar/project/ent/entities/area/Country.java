package ar.project.ent.entities.area;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.project.ent.annotations.ColumnName;
import ar.project.ent.base.AbstractEntity;

//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "countries")
public class Country extends AbstractEntity<Integer> {

	private Integer id;
	private String name;
	private String code;

	@ColumnName
	public static final String NAME = new String();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@SequenceGenerator(name = "pais_sequence", sequenceName = "pais_sequence")
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "pais_sequence")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 60)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code", length = 60)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/*static {
		// This must be at the end
		Class<?> clazz = new Object() {
		}.getClass().getEnclosingClass();
		InjectColumnName(clazz);
	}*/

}
