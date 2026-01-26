package com.accesscontrol.model;

/**
 * Concrete Document resource.
 * OOPS: Inheritance (IS-A relationship with Resource)
 */
public class Document extends Resource {

    public Document(String id, String name) {
        super(id, name, "DOCUMENT");
    }

    @Override
    public String getDescription() {
        return "Document: " + getName();
    }
}