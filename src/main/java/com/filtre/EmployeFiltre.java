package com.filtre;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EmployeFiltre implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        if (session.getAttribute("role").equals("employe") || session.getAttribute("role").equals("admin") || session.getAttribute("menu").equals("menu:admin") || session.getAttribute("menu").equals("menu:employe")) {

            chain.doFilter( request, response );
        } else {
            String erreur = "Vous n'avez pas les droits pour cette page !";
            String retour = "/accueil";
            session.setAttribute("erreur", erreur);
            session.setAttribute("retour", retour);
            response.sendRedirect("erreur");
        }
    }
    @Override
    public void destroy() {

    }
}


