package com.hcl.bootcamp.fs.springboot.app.validator;

import com.hcl.bootcamp.fs.springboot.app.model.User;
import com.hcl.bootcamp.fs.springboot.app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        System.out.println("1." + user.getFirstName());
        System.out.println("2." + user.getLastName());
        System.out.println("3." + user.getEmail());
        System.out.println("4." + user.getPassword());
        System.out.println("5." + user.getPasswordConfirm());
        System.out.println("6." + user.getLocation());
        System.out.println("7." + user.getState());
        
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

        if (user.getFirstName().length() < 6 || user.getFirstName().length() > 32) {
            errors.rejectValue("firstName", "Size.userForm.firstName");
        }
        if (user.getFirstName().length() < 6 || user.getFirstName().length() > 32) {
            errors.rejectValue("lastName", "Size.userForm.firstName");
        }        
        if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
            errors.rejectValue("email", "Size.userForm.firstName");
        }        
        if (userService.findByUsername(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.username");
        }        
        if (user.getLocation().length() < 6 || user.getLocation().length() > 32) {
            errors.rejectValue("location", "Size.userForm.location");
        }                 
        


       // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
