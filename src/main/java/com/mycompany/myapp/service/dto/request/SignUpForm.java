package com.mycompany.myapp.service.dto.request;

import java.util.Set;

public class SignUpForm {

    private String name;
    private String email;
    private String password;
    private Set<String> roles;

    public SignUpForm(String name, String email, String password, Set<String> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public SignUpForm() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return (
            "SignUpForm{" +
            "name='" +
            name +
            '\'' +
            ", email='" +
            email +
            '\'' +
            ", password='" +
            password +
            '\'' +
            ", roles=" +
            roles +
            '}'
        );
    }
}
