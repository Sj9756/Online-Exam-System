package com.santosh.oes.model;


public enum Roles{
    ADMIN,STUDENT,TEACHER;
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
