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
@Table(name = "tags")
public class PersonTag extends AbstractEntity<Integer> {

	private Integer id;
	private String tag;
	
	@ColumnName
	public static final String PERSONA = EMPTY;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "tags", length = 20)
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
