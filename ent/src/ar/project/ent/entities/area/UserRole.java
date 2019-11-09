package ar.project.ent.entities.area;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ar.project.ent.base.AbstractEntity;

@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(columnNames = {
		"role", "username" }))
public class UserRole extends AbstractEntity<Integer> {

	private Integer userRoleId;
	private User user;
	private String role;
	private String userName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@SequenceGenerator(name = "urol_sequence", sequenceName = "urol_sequence")
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "urol_sequence")
	@Column(name = "user_role_id", unique = true, nullable = false)
	public Integer getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "role", nullable = false, length = 45)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Column(name = "username", updatable = false, insertable = false, length=45)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
