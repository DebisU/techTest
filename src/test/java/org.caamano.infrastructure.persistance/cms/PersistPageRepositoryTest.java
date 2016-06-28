package org.caamano.infrastructure.persistance.cms;

import org.caamano.domain.management.Page;
import org.caamano.infrastructure.persistence.management.PersistPageRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersistPageRepositoryTest {

    PersistPageRepository sut;

    @Before
    public void setUp() throws Exception {
        sut = new PersistPageRepository();
    }

    @Test
    public void shouldLetAddPages() throws Exception {
        String aName = "aUsername";
        String aRoles = "aRole";
        Page page = new Page(aName, aRoles);

        sut.add(page);

        assertEquals(1, sut.allPages().length);
    }

    @Test
    public void shouldFindPageByName() throws Exception {
        String aName = "aName";
        Page expectedPage = new Page(aName, "aRole");
        sut.add(expectedPage);

        Page actual = sut.pageByName(aName);

        assertSame(expectedPage, actual);
    }

    @Test
    public void shouldReturnNullWhenNoPageFoundByName() throws Exception {
        String aName = "aName";
        String aDifferentName = "aDifferentName";
        Page expectedPage = new Page(aName, "aRole");
        sut.add(expectedPage);

        Page actual = sut.pageByName(aDifferentName);

        assertNull(actual);
    }
}