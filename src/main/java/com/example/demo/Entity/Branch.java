package com.example.demo.Entity;

public class Branch {

    String name;
    String sha;

    public Branch(String name, String sha) {
        this.name = name;
        this.sha = sha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }
}
