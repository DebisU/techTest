package org.caamano.domain.authorization;

import org.caamano.domain.authentication.UserSession;
import org.caamano.domain.authentication.UserSessionRepository;
import org.caamano.domain.management.Page;

import java.util.ArrayList;
import java.util.Arrays;

public class PageAuthorizationService {

    private UserSessionRepository sessionRepository;

    public PageAuthorizationService(UserSessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public boolean authorize(Page page, UserSession session) throws LoginRequiredException, SessionExpiredException {
        if (session == null) {
            throw new LoginRequiredException();
        }
        if (!sessionRepository.validate(session)) {
            throw new SessionExpiredException();
        }
        String requiredRole = page.requiredRole();
        String[] userRoles = session.userRoles();
        ArrayList<String> userRolesList = new ArrayList<String>(Arrays.asList(userRoles));

        return userRolesList.contains(requiredRole);
    }
}
