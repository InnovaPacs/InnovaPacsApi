package innova.pacs.api.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

import innova.pacs.api.dto.UserDto;

@SqlResultSetMapping(
		name = "mappingUserQueryDto", 
		classes = {
				@ConstructorResult(
						targetClass = UserDto.class, 
						columns = { 
								@ColumnResult(type = Long.class, name = "id"),
								@ColumnResult(type = String.class,name = "email"), 
								@ColumnResult(type = String.class,name = "username"), 
								@ColumnResult(type = Boolean.class,name = "active"),
								@ColumnResult(type = String.class,name = "institutions")
								}) 
				})
@NamedNativeQuery(
		name = "User.userReportQuery", 
		query = "select \n" + 
				"suser.id as id,\n" + 
				"suser.email as email,\n" + 
				"suser.username as username,\n" + 
				"suser.active as active,\n" + 
				"(select distinct string_agg(name,', ') from institution_user iu\n" + 
				"	join institutions institution ON iu.institution_id = institution.id where iu.user_id = suser.id) as institutions\n" + 
				"from sec_users suser", 
		resultSetMapping = "mappingUserQueryDto")
@Entity
@Table(name = "sec_users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String email;
	@JsonIgnore
	private String password;
	@Column(unique = true)
	private String username;
	private Date createdAt;
	private Date updatedAt;
	private Boolean active;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="user_role", joinColumns= @JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="role_id"),
	uniqueConstraints= {@UniqueConstraint(columnNames= {"user_id", "role_id"})})
	private List<Role> roles;
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
