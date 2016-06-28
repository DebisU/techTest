package org.caamano.infrastructure.persistance.user;

import org.caamano.domain.user.User;
import org.caamano.infrastructure.persistence.user.PersistUserRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersistUserRepositoryTest {

    PersistUserRepository sut;

    @Before
    public void setUp() throws Exception {
        sut = new PersistUserRepository();
    }

    @Test
    public void shouldLetAddUsers() throws Exception {
        String aUsername = "aUsername";
        String[] someRoles = {"aRole"};
        User user = User.fromRegistration(aUsername, someRoles);

        sut.add(user);

        assertEquals(1, sut.allUsers().length);
    }

    @Test
    public void shouldFindUserByUsername()
    {
        String aUsername = "aUsername";
        User expectedUser = User.fromRegistration(aUsername, new String[]{"aRole"});
        sut.add(expectedUser);

        User actual = sut.userByUsername(aUsername);

        assertSame(expectedUser, actual);
    }

    @Test
    public void shouldReturnNullWhenNoUserFoundByUsername() throws Exception {
        String aUsername = "aUsername";
        String aDifferentUsername = "aDifferentUsername";
        User expectedUser = User.fromRegistration(aUsername, new String[]{"aRole"});
        sut.add(expectedUser);

        User actual = sut.userByUsername(aDifferentUsername);

        assertNull(actual);
    }
}