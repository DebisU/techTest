package org.caamano.infrastructure.persistance.authentication;

import org.caamano.domain.authentication.UserSession;
import org.caamano.infrastructure.persistence.authentication.PersistSessionRepository;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class PersistSessionRepositoryTest {

    private PersistSessionRepository sut;



    @Before
    public void setUp() throws Exception {
        sut = new PersistSessionRepository();
    }

    @Test
    public void shouldLetAddUserSessions() throws Exception {
        UserSession session = new UserSession("aUsername", new String[]{"aRole"});

        sut.add(session);

        assertEquals(1, sut.findAllSessions().length);
    }

    @Test
    public void shouldLetInvalidateSessions() throws Exception {
        UserSession session = new UserSession("aUsername", new String[]{"aRole"});
        sut.add(session);

        sut.invalidate(session);

        assertEquals(0, sut.findAllSessions().length);
    }

    @Test
    public void shouldValidateSessions() throws Exception {
        UserSession session = new UserSession("aUsername", new String[]{"aRole"});
        sut.add(session);

        boolean actual = sut.validate(session);

        assertTrue(actual);
    }

    @Test
    public void shouldNotValidateSessionsWhenDoesNotExist() throws Exception {
        UserSession session = new UserSession("aUsername", new String[]{"aRole"});

        boolean actual = sut.validate(session);

        assertFalse(actual);
    }

    @Test
    public void shouldNotValidateSessionsWhenHasExpired() throws Exception {
        UserSession session = new UserSession("aUsername", new String[]{"aRole"}, -1);
        sut.add(session);

        boolean actual = sut.validate(session);

        assertFalse(actual);
    }
}