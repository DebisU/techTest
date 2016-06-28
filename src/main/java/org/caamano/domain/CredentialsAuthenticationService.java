package org.caamano.domain;

import org.caamano.domain.authentication.InvalidArgumentException;
import org.caamano.domain.user.User;
import org.caamano.domain.user.UserCredentials;
import org.caamano.domain.user.UserRepository;

public class CredentialsAuthenticationService {

    private UserRepository userRepository;
    private UserSessionRepository sessionRepository;

    public CredentialsAuthenticationService(UserRepository userRepository, UserSessionRepository sessionRepository) {

        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    public UserSession authenticate(String username, UserCredentials credentials) throws InvalidArgumentException {
        User user = userRepository.userByUsername(username);
        if (user == null) {
            throw new InvalidArgumentException();
        }
        UserCredentials toValidate = UserCredentials.fromUsername(username);
        if (credentials.equals(toValidate)) {
            UserSession session = new UserSession(username, user.roles());
            sessionRepository.add(session);

            return session;
        }

        return null;
    }
}
