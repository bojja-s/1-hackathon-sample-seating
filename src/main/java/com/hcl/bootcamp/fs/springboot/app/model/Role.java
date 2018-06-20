package com.hcl.bootcamp.fs.springboot.app.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "roles")
@Data
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="users_roles",
    joinColumns={@JoinColumn(name="role_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="id")})
    private Collection<User> users;
}
