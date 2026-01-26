package com.accesscontrol.audit;

import com.accesscontrol.model.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Simple audit logger that prints access attempts.
 * OOPS: Single Responsibility (only handles logging)
 */
public class SimpleAuditLogger {
    private int allowedCount = 0;
    private int deniedCount = 0;

    public void logAllowed(User user, Action action, Resource resource) {
        allowedCount++;
        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("[" + timestamp + "] ✓ ALLOWED: " +
                user.getName() + " → " + action +
                " → " + resource.getName());
    }

    public void logDenied(User user, Action action, Resource resource) {
        deniedCount++;
        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("[" + timestamp + "] ✗ DENIED: " +
                user.getName() + " → " + action +
                " → " + resource.getName());
    }

    public void printSummary() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("AUDIT SUMMARY");
        System.out.println("=".repeat(60));
        System.out.println("Total Allowed: " + allowedCount);
        System.out.println("Total Denied:  " + deniedCount);
        System.out.println("=".repeat(60) + "\n");
    }
}