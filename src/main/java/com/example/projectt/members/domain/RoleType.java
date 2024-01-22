package com.example.projectt.members.domain;

public enum RoleType {
    USER, ADMIN, DORMANCY, BLOCK;

    @Override
    public String toString() {
        return name();
    }
}
