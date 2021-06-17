package com.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author Vanconingsloo Kevin
 */

@WebServlet("/erreur")
public class ErreurServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/erreur.jsp").forward(request, response);
        session.removeAttribute("erreur");
        session.removeAttribute("retour");
    }
}
