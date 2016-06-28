package org.caamano.domain.user;

public class User {
    private final String username;
    private final String[] roles;

    private User(String username, String[] roles) {
        this.username = username;
        this.roles = roles;
    }

    public static User fromRegistration(String username, String[] roles) {
        return new User(username, roles);
    }

    public String username() {
        return this.username;
    }

    public String[] roles() {
        return this.roles;
    }

    public UserCredentials credentials() {
        return UserCredentials.fromUsername(username);
    }
}
