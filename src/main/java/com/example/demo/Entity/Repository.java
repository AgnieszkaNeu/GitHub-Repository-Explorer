package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class Repository {

    String login;
    String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<Branch> branches;

    public Repository(String login, String name) {
        this.login = login;
        this.name = name;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Branch> getBranches() {
        return branches;
    }
}
