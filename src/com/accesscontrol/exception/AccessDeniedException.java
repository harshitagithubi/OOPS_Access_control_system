package com.accesscontrol.exception;

import com.accesscontrol.model.*;

/**
 * Custom exception for access denial.
 * OOPS: Exception Handling
 */
public class AccessDeniedException extends Exception {

    public AccessDeniedException(User user, Action action, Resource resource) {
        super("Access Denied: " + user.getName() +
                " cannot perform " + action +
                " on " + resource.getName());
    }
}