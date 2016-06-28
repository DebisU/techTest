package org.caamano.infrastructure.persistence.management;

import org.caamano.domain.management.Page;
import org.caamano.domain.management.PageRepository;

import java.util.ArrayList;
import java.util.List;

public class PersistPageRepository implements PageRepository {
    List<Page> elements;

    public PersistPageRepository() {
        elements = new ArrayList<Page>();
    }

    public void add(Page page) {
        elements.add(page);
    }

    public Page pageByName(String name) {
        for( Page page: elements.toArray(new Page[elements.size()])) {
            if (page.name().equals(name)) {
                return page;
            }
        }

        return null;
    }

    public Page pageByUserRole(String userRole) {
        for(Page page : elements.toArray(new Page[elements.size()])) {
            if (page.requiredRole().equals(userRole)) {
                return page;
            }
        }
        return null;
    }


    public Page[] allPages() {
        return elements.toArray(new Page[elements.size()]);
    }
}
