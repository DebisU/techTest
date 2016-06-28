package org.caamano.infrastructure.persistence.user;

import org.caamano.domain.user.User;
import org.caamano.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class PersistUserRepository implements UserRepository {

    List<User> elements;

    public PersistUserRepository() {
        elements = new ArrayList<User>();
    }

    public void add(User user) {
        elements.add(user);
    }

    public User userByUsername(String username) {
        for(User user : elements.toArray(new User[elements.size()])) {
            if (user.username().equals(username)) {
                return user;
            }
        }
        return null;
    }



    public User[] allUsers() {
        return elements.toArray(new User[elements.size()]);
    }
}
