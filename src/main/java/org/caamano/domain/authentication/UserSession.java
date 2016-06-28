package org.caamano.domain.authentication;

import org.joda.time.DateTime;

public class UserSession {

    public static final int EXPIRING_IN_MINUTES = 5;
    private String username;
    private DateTime expiresAt;
    private String[] userRoles;

    public UserSession(String username, String[] userRoles) {
        this(username, userRoles, EXPIRING_IN_MINUTES);
    }

    public UserSession(String username, String[] userRoles, int expiringInMinutes) {
        this.username = username;
        this.userRoles = userRoles;
        this.expiresAt = new DateTime().plusMinutes(expiringInMinutes);
    }

    public String username() {
        return username;
    }

    public String[] userRoles() {
        return userRoles;
    }

    public DateTime expiresAt() {
        return expiresAt;
    }

    public void increaseExpiringDate(int minutes) {
        expiresAt = expiresAt.plusMinutes(minutes);
    }

    public void expire() {
        this.expiresAt = new DateTime().minusSeconds(1);
    }
}
