package com.accesscontrol.model;

import java.util.*;

/**
 * Represents a role with permissions and optional parent role.
 * Demonstrates role hierarchy where child roles inherit parent permissions.
 * 
 * OOPS: Composition (Role HAS-A parent Role) + Recursion
 * Real-world: ADMIN > MANAGER > EMPLOYEE
 */
public class Role {
    private final String name;
    private final Set<Permission> permissions;
    private Role parentRole; // NEW: Parent role for inheritance

    public Role(String name) {
        this.name = name;
        this.permissions = new HashSet<>();
        this.parentRole = null;
    }

    public String getName() {
        return name;
    }

    /**
     * Sets the parent role from which this role inherits permissions.
     * 
     * @param parentRole The parent role
     */
    public void setParentRole(Role parentRole) {
        this.parentRole = parentRole;
    }

    public Role getParentRole() {
        return parentRole;
    }

    /**
     * Gets only the direct permissions of this role (not inherited).
     */
    public Set<Permission> getDirectPermissions() {
        return Collections.unmodifiableSet(permissions);
    }

    /**
     * Gets all permissions including inherited from parent roles.
     * Uses recursion to traverse the role hierarchy.
     * 
     * OOPS Concept: Recursive composition
     */
    public Set<Permission> getAllPermissions() {
        Set<Permission> allPermissions = new HashSet<>(permissions);
        
        // Recursively add parent's permissions
        if (parentRole != null) {
            allPermissions.addAll(parentRole.getAllPermissions());
        }
        
        return allPermissions;
    }

    public void addPermission(Permission permission) {
        permissions.add(permission);
    }

    public boolean hasPermission(Permission permission) {
        return getAllPermissions().contains(permission);
    }

    @Override
    public String toString() {
        String parentInfo = parentRole != null ? " (inherits from " + parentRole.getName() + ")" : "";
        return "Role{" + name + ", direct permissions=" + permissions.size() + 
               ", total permissions=" + getAllPermissions().size() + parentInfo + "}";
    }
}