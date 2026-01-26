package com.accesscontrol.policy;

import com.accesscontrol.model.*;

/**
 * Role-Based Access Control implementation.
 * OOPS: Polymorphism (implements AccessPolicy interface)
 */
public class RBACPolicy implements AccessPolicy {

    @Override
    public boolean authorize(User user, Action action, Resource resource) {
        // Create required permission
        Permission required = new Permission(action, resource.getType());

        // Check if user has this permission through any role
        return user.getAllPermissions().contains(required);
    }

    @Override
    public String getPolicyName() {
        return "Role-Based Access Control (RBAC)";
    }
}