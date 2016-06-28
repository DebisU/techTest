package org.caamano.domain.authorization;

public class SessionExpiredException extends Throwable {
    public SessionExpiredException() {
        super("Session has expired");
    }
}
