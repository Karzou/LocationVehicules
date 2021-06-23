package com.controller;

import com.connection.EMF;
import com.entity.Marque;
import com.entity.Modele;
import com.exception.ServiceException;
import com.service.MarqueService;
import com.service.ModeleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/gestionMarqueModele")
public class GestionMarqueModeleServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(GestionMarqueModeleServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        MarqueService marqueService = new MarqueService(em);
        ModeleService modeleService = new ModeleService(em);
        List<Marque> marqueList = null;
        List<Modele> modeleList = null;

        try {

            modeleList = modeleService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            marqueList = marqueService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("marqueList", marqueList);
        request.setAttribute("modeleList", modeleList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/gestionMarqueModele.jsp").forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        int id = Integer.parseInt(request.getParameter("idModif"));
        boolean flagModifMarque = Boolean.parseBoolean(request.getParameter("flagModifMarque"));

        MarqueService marqueService = new MarqueService(em);
        ModeleService modeleService = new ModeleService(em);
        Marque marque = null;
        Modele modele = null;

        if(flagModifMarque) {

            try {

                marque = marqueService.trouver(id);
            } catch (ServiceException e) {

                e.printStackTrace();
            }

            try {

                marque.setNomMarque(request.getParameter("nomMarque"));

                transaction.begin();

                marqueService.update(marque);

                transaction.commit();
            } catch ( Exception e ) {

                throw new ServletException( e );
            } finally {

                if (transaction.isActive()) {

                    transaction.rollback();
                }

                em.close();
            }
        } else {

            try {

                modele = modeleService.trouver(id);
            } catch (ServiceException e) {

                e.printStackTrace();
            }

            try {

                modele.setNomModele(request.getParameter("nomModele"));

                transaction.begin();

                modeleService.update(modele);

                transaction.commit();
            } catch ( Exception e ) {

                throw new ServletException( e );
            } finally {

                if (transaction.isActive()) {

                    transaction.rollback();
                }

                em.close();
            }
        }

        response.sendRedirect("gestionMarqueModele");
    }
}
