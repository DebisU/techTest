package org.caamano.infrastructure.http;

import org.caamano.domain.authentication.UserSession;
import org.caamano.domain.authorization.LoginRequiredException;
import org.caamano.domain.authorization.PageAuthorizationService;
import org.caamano.domain.authorization.SessionExpiredException;
import org.caamano.domain.management.Page;
import org.caamano.domain.management.PageRepository;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PageServlet extends HttpServlet implements Servlet {

    private PageRepository pageRepository;
    private PageAuthorizationService pageAuthorizationService;
    private final int USER_ROLE = 0;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        pageRepository = (PageRepository) config.getServletContext().getAttribute("pageRepository");
        pageAuthorizationService = (PageAuthorizationService) config.getServletContext().getAttribute("pageAuthorizationService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        final HttpSession session = request.getSession(true);
        final Object userSession = session.getAttribute("userSession");
        String userRole = null;
        try {
            userRole = ((UserSession) userSession).userRoles()[USER_ROLE];
        } catch (Exception e) {
            request.getRequestDispatcher("/error403.jsp").forward(request, response);
        }

        Page page = pageRepository.pageByUserRole(userRole);
        UserSession currentSession = (UserSession) request.getSession().getAttribute("userSession");
        try {
            if (page != null) {
                boolean isAuthorized = pageAuthorizationService.authorize(page, currentSession);
                if (isAuthorized) {
                    request.setAttribute("username", currentSession.username());
                    request.setAttribute("pageName", page.name());
                    request.getRequestDispatcher("/page.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/error403.jsp").forward(request, response);
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
            }
        } catch (LoginRequiredException | SessionExpiredException e) {
            response.sendRedirect("login");
        }
    }
}
