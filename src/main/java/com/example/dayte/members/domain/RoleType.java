package com.example.dayte.members.domain;

public enum RoleType {
    USER, ADMIN, DORMANCY, BLOCK;

    @Override
    public String toString() {
        return name();
    }
}
