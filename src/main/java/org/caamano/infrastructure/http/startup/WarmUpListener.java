package org.caamano.infrastructure.http.startup;

import org.caamano.domain.authentication.CredentialsAuthenticationService;
import org.caamano.domain.authentication.UserSessionRepository;
import org.caamano.domain.authorization.PageAuthorizationService;
import org.caamano.domain.management.Page;
import org.caamano.domain.management.PageRepository;
import org.caamano.domain.user.User;
import org.caamano.domain.user.UserRepository;
import org.caamano.infrastructure.persistence.authentication.PersistSessionRepository;
import org.caamano.infrastructure.persistence.management.PersistPageRepository;
import org.caamano.infrastructure.persistence.user.PersistUserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WarmUpListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        initPageRepository(servletContext);
        initUserRepository(servletContext);
        initUserSessionRepository(servletContext);
        initApplicationServices(servletContext);
    }

    private void initPageRepository(ServletContext servletContext) {
        PageRepository pageRepository = new PersistPageRepository();
        pageRepository.add(new Page("page1", "PAG_1"));
        pageRepository.add(new Page("page2", "PAG_2"));
        pageRepository.add(new Page("page3", "PAG_3"));

        servletContext.setAttribute("pageRepository", pageRepository);
    }

    private void initUserRepository(ServletContext servletContext) {
        UserRepository userRepository = new PersistUserRepository();
        userRepository.add(User.fromRegistration("user1", new String[] {"PAG_1"}));
        userRepository.add(User.fromRegistration("user2", new String[] {"PAG_2"}));
        userRepository.add(User.fromRegistration("user3", new String[] {"PAG_3"}));

        servletContext.setAttribute("userRepository", userRepository);
    }

    private void initUserSessionRepository(ServletContext servletContext) {
        UserSessionRepository sessionRepository = new PersistSessionRepository();
        servletContext.setAttribute("sessionRepository", sessionRepository);
    }

    private void initApplicationServices(ServletContext servletContext) {
        UserSessionRepository sessionRepository = (UserSessionRepository) servletContext.getAttribute("sessionRepository");
        UserRepository userRepository = (UserRepository) servletContext.getAttribute("userRepository");

        PageAuthorizationService pageAuthorizationService = new PageAuthorizationService(sessionRepository);
        servletContext.setAttribute("pageAuthorizationService", pageAuthorizationService);

        CredentialsAuthenticationService credentialsAuthenticationService = new CredentialsAuthenticationService(userRepository, sessionRepository);
        servletContext.setAttribute("credentialsAuthenticationService", credentialsAuthenticationService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
