package com.accesscontrol.model;

/**
 * Abstract base class for all protected resources.
 * OOPS: Abstraction & Inheritance (template for concrete resources)
 */
public abstract class Resource {
    private final String id;
    private final String name;
    private final String type;

    protected Resource(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    // Abstract method - subclasses must implement
    public abstract String getDescription();

    @Override
    public String toString() {
        return type + ": " + name;
    }
}