package com.accesscontrol.model;

import java.util.Objects;

/**
 * Represents a permission - combination of Action and Resource Type.
 * OOPS: Encapsulation (private fields), Immutability (final fields)
 */
public class Permission {
    private final Action action;
    private final String resourceType;

    public Permission(Action action, String resourceType) {
        this.action = action;
        this.resourceType = resourceType;
    }

    public Action getAction() {
        return action;
    }

    public String getResourceType() {
        return resourceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Permission that = (Permission) o;
        return action == that.action &&
                Objects.equals(resourceType, that.resourceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, resourceType);
    }

    @Override
    public String toString() {
        return action + " on " + resourceType;
    }
}