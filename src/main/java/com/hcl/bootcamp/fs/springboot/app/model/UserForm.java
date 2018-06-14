package com.hcl.bootcamp.fs.springboot.app.model;

import lombok.Data;

@Data
public class UserForm {

	private String firstName;
	private String lastName;
	private String email;
	private String location;
	private String country;
	private String state;
	private String password;
	private String passwordConfirm;

}
