package com.accesscontrol.demo;

import com.accesscontrol.model.*;
import com.accesscontrol.policy.*;
import com.accesscontrol.exception.*;
import com.accesscontrol.audit.*;

/**
 * Main demonstration of the Access Control System.
 * Demonstrates all OOPS concepts with clear scenarios.
 */
public class AccessControlDemo {

    private AccessPolicy policy;
    private SimpleAuditLogger auditLogger;

    public static void main(String[] args) {
        AccessControlDemo demo = new AccessControlDemo();
        demo.run();
    }

    public void run() {
        printHeader("ACCESS CONTROL SYSTEM - OOPS DEMONSTRATION");

        // Initialize policy and audit logger
        policy = new RBACPolicy();
        auditLogger = new SimpleAuditLogger();

        System.out.println("Using Policy: " + policy.getPolicyName() + "\n");

        // Create roles
        Role admin = createAdminRole();
        Role manager = createManagerRole();
        Role employee = createEmployeeRole();

        // Create users
        User adminUser = createUser("admin-001", "John Admin", admin);
        User managerUser = createUser("mgr-001", "Sarah Manager", manager);
        User employeeUser = createUser("emp-001", "Bob Employee", employee);
        User guestUser = createUser("guest-001", "Alice Guest", null);

        System.out.println(); // Blank line for readability

        // Create resources
        Resource doc1 = new Document("doc-001", "Employee Handbook");
        Resource report1 = new Report("rpt-001", "Sales Report");

        // Run test scenarios
        testScenario1(adminUser, doc1, report1);
        testScenario2(managerUser, doc1, report1);
        testScenario3(employeeUser, doc1, report1);
        testScenario4(guestUser, doc1, report1);

        // Print audit summary
        auditLogger.printSummary();
    }

    /**
     * Creates an Admin role with full permissions.
     */
    private Role createAdminRole() {
        System.out.println("Creating ADMIN role...");
        Role admin = new Role("ADMIN");

        // Admin has all permissions
        admin.addPermission(new Permission(Action.READ, "DOCUMENT"));
        admin.addPermission(new Permission(Action.WRITE, "DOCUMENT"));
        admin.addPermission(new Permission(Action.DELETE, "DOCUMENT"));
        admin.addPermission(new Permission(Action.EXECUTE, "DOCUMENT"));
        admin.addPermission(new Permission(Action.READ, "REPORT"));
        admin.addPermission(new Permission(Action.WRITE, "REPORT"));
        admin.addPermission(new Permission(Action.DELETE, "REPORT"));
        admin.addPermission(new Permission(Action.EXECUTE, "REPORT"));

        System.out.println("  ✓ Created with " + admin.getPermissions().size() + " permissions");
        return admin;
    }

    /**
     * Creates a Manager role with limited permissions.
     */
    private Role createManagerRole() {
        System.out.println("Creating MANAGER role...");
        Role manager = new Role("MANAGER");

        // Manager can read and write, but not delete
        manager.addPermission(new Permission(Action.READ, "DOCUMENT"));
        manager.addPermission(new Permission(Action.WRITE, "DOCUMENT"));
        manager.addPermission(new Permission(Action.READ, "REPORT"));
        manager.addPermission(new Permission(Action.WRITE, "REPORT"));

        System.out.println("  ✓ Created with " + manager.getPermissions().size() + " permissions");
        return manager;
    }

    /**
     * Creates an Employee role with read-only permissions.
     */
    private Role createEmployeeRole() {
        System.out.println("Creating EMPLOYEE role...");
        Role employee = new Role("EMPLOYEE");

        // Employee can only read
        employee.addPermission(new Permission(Action.READ, "DOCUMENT"));
        employee.addPermission(new Permission(Action.READ, "REPORT"));

        System.out.println("  ✓ Created with " + employee.getPermissions().size() + " permissions");
        return employee;
    }

    /**
     * Creates a user and assigns a role.
     */
    private User createUser(String id, String name, Role role) {
        User user = new User(id, name);
        if (role != null) {
            user.assignRole(role);
            System.out.println("Created user: " + name + " [" + role.getName() + "]");
        } else {
            System.out.println("Created user: " + name + " [NO ROLE]");
        }
        return user;
    }

    /**
     * Scenario 1: Admin user (should have full access).
     */
    private void testScenario1(User admin, Resource doc, Resource report) {
        printSection("Scenario 1: ADMIN Access (Full Permissions)");

        attemptAccess(admin, Action.READ, doc);
        attemptAccess(admin, Action.WRITE, doc);
        attemptAccess(admin, Action.DELETE, doc);
        attemptAccess(admin, Action.READ, report);
        attemptAccess(admin, Action.DELETE, report);

        System.out.println();
    }

    /**
     * Scenario 2: Manager user (read/write only, no delete).
     */
    private void testScenario2(User manager, Resource doc, Resource report) {
        printSection("Scenario 2: MANAGER Access (Read/Write Only)");

        attemptAccess(manager, Action.READ, doc);
        attemptAccess(manager, Action.WRITE, doc);
        attemptAccess(manager, Action.DELETE, doc); // Should FAIL
        attemptAccess(manager, Action.WRITE, report);
        attemptAccess(manager, Action.DELETE, report); // Should FAIL

        System.out.println();
    }

    /**
     * Scenario 3: Employee user (read-only access).
     */
    private void testScenario3(User employee, Resource doc, Resource report) {
        printSection("Scenario 3: EMPLOYEE Access (Read Only)");

        attemptAccess(employee, Action.READ, doc);
        attemptAccess(employee, Action.READ, report);
        attemptAccess(employee, Action.WRITE, doc); // Should FAIL
        attemptAccess(employee, Action.DELETE, report); // Should FAIL

        System.out.println();
    }

    /**
     * Scenario 4: Guest user with no role (no access).
     */
    private void testScenario4(User guest, Resource doc, Resource report) {
        printSection("Scenario 4: GUEST with NO ROLE (No Access)");

        attemptAccess(guest, Action.READ, doc); // Should FAIL
        attemptAccess(guest, Action.WRITE, doc); // Should FAIL
        attemptAccess(guest, Action.READ, report); // Should FAIL

        System.out.println();
    }

    /**
     * Core method to attempt access and log results.
     */
    private void attemptAccess(User user, Action action, Resource resource) {
        try {
            // Use policy to check authorization
            boolean authorized = policy.authorize(user, action, resource);

            if (authorized) {
                auditLogger.logAllowed(user, action, resource);
            } else {
                auditLogger.logDenied(user, action, resource);
                throw new AccessDeniedException(user, action, resource);
            }

        } catch (AccessDeniedException e) {
            // Exception already logged by audit logger
        }
    }

    /**
     * Prints a formatted header.
     */
    private void printHeader(String title) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  " + title);
        System.out.println("=".repeat(60) + "\n");
    }

    /**
     * Prints a formatted section title.
     */
    private void printSection(String title) {
        System.out.println("-".repeat(60));
        System.out.println(title);
        System.out.println("-".repeat(60));
    }
}
