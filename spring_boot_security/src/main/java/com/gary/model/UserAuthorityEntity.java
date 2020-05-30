package com.gary.model;

public class UserAuthorityEntity {

    private Integer id;
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserAuthorityEntity{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }

}
