package org.caamano.domain.user;

public class UserCredentials {
    private String username;

    private UserCredentials(String username) {
        this.username = username;
    }

    public static UserCredentials fromUsername(String username) {
        return new UserCredentials(username);
    }

    public String username() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        UserCredentials other = (UserCredentials) obj;
        return this.username.equals(other.username());
    }
}
