package org.caamano.domain.management;

public class Page {
    private final String name;
    private final String requiredRole;

    public Page(String name, String requiredRole) {
        this.name = name;
        this.requiredRole = requiredRole;
    }

    public String name() {
        return name;
    }

    public String requiredRole() {
        return requiredRole;
    }
}
