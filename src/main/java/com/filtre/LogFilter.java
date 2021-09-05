package com.filtre;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Vanconingsloo Kevin
 */
public class LogFilter implements Filter {

    public void init( FilterConfig config ) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
            ServletException {
        /* Cast des objets request et response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /*
          Si l'objet utilisateur n'existe pas dans la session en cours, alors
          l'utilisateur n'est pas connecté.
         */
        if ( session.getAttribute( "idUtilisateur" ) == null ) {
            /* Redirection vers la page login */
            response.sendRedirect("login");
        } else {
            /* Affichage de la page restreinte */
            chain.doFilter( request, response );
        }
    }

    public void destroy() {
    }
}
