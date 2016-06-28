package org.caamano.infrastructure.http;

import org.caamano.domain.authentication.UserSession;
import org.caamano.domain.authentication.UserSessionRepository;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet implements Servlet {

    private UserSessionRepository sessionRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sessionRepository = (UserSessionRepository) config.getServletContext().getAttribute("sessionRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserSession currentSession = (UserSession) request.getSession().getAttribute("userSession");
        if (currentSession != null) {
            sessionRepository.invalidate(currentSession);
            request.getSession().setAttribute("userSession", null);
        }
        response.sendRedirect("login");
    }
}
