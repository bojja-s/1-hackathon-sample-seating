package com.hcl.bootcamp.fs.springboot.app.model;


import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Entity(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Long id;
	private Date createdAt;
	private Date updatedAt;
	private boolean enable;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String location;
	private String country;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="users_roles",
    joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    private Collection<Role> roles;
}

