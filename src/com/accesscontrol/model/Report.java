package com.accesscontrol.model;

/**
 * Concrete Report resource.
 * OOPS: Inheritance (IS-A relationship with Resource)
 */
public class Report extends Resource {

    public Report(String id, String name) {
        super(id, name, "REPORT");
    }

    @Override
    public String getDescription() {
        return "Report: " + getName();
    }
}