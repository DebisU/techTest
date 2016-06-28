package org.caamano.infrastructure.http;

import org.caamano.domain.authentication.CredentialsAuthenticationService;
import org.caamano.domain.authentication.InvalidArgumentException;
import org.caamano.domain.authentication.UserSession;
import org.caamano.domain.user.User;
import org.caamano.domain.user.UserCredentials;
import org.caamano.domain.user.UserRepository;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet implements Servlet {

    private UserRepository userRepository;
    private CredentialsAuthenticationService credentialsAuthenticationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userRepository = (UserRepository) config.getServletContext().getAttribute("userRepository");
        credentialsAuthenticationService = (CredentialsAuthenticationService) config.getServletContext().getAttribute("credentialsAuthenticationService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("user");
        User user = userRepository.userByUsername(username);
        try {
            if (user != null) {
                UserCredentials credentials = user.credentials();
                UserSession session = credentialsAuthenticationService.authenticate(username, credentials);
                request.getSession().setAttribute("userSession", session);
                response.sendRedirect("page");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            }
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
    }
}
