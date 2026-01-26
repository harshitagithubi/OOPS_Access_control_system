package com.accesscontrol.model;

import java.util.*;

/**
 * Represents a user with assigned roles.
 * OOPS: Composition (User HAS-A Set of Roles)
 */
public class User {
    private final String id;
    private final String name;
    private final Set<Role> roles;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.roles = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public void assignRole(Role role) {
        roles.add(role);
    }

    // Get all permissions from all roles
    public Set<Permission> getAllPermissions() {
        Set<Permission> allPermissions = new HashSet<>();
        for (Role role : roles) {
            allPermissions.addAll(role.getPermissions());
        }
        return allPermissions;
    }

    @Override
    public String toString() {
        return "User{" + name + " (" + id + ")}";
    }
}