package com.accesscontrol.policy;

import com.accesscontrol.model.*;

/**
 * Interface for access control policies.
 * OOPS: Abstraction (defines contract without implementation)
 * Design Pattern: Strategy Pattern
 */
public interface AccessPolicy {
    boolean authorize(User user, Action action, Resource resource);

    String getPolicyName();
}