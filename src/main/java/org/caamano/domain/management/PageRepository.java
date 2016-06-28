package org.caamano.domain.management;

public interface PageRepository {
    void add(Page page);

    Page pageByName(String name);
    Page pageByUserRole(String role);
}
