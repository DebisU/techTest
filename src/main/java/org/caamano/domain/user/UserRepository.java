package org.caamano.domain.user;

public interface UserRepository {
    void add(User user);

    User userByUsername(String username);
}
