package com.controller;

import com.connection.EMF;
import com.entity.Facture;
import com.entity.Reservation;
import com.exception.ServiceException;
import com.service.FactureService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/facture")
public class FactureServlet extends HttpServlet {
    final static Logger logger = LogManager.getLogger(FactureServlet.class);


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("je passe par la page facture");

        EntityManager em = EMF.getEM();

        FactureService factureService = new FactureService(em);

        List<Facture> factureList = null;
        List<Facture> myFactureList = null;

        HttpSession session = request.getSession();
        Integer idUtilisateur = (Integer) session.getAttribute("idUtilisateur");
        try {
            factureList = factureService.findAll();
            Set<Integer> myFactureIds = new HashSet<>();

            for (Facture facture : factureList) {
                Reservation reservation = facture.getContratsByIdContrat().getReservation();
                if (reservation.getUtilisateursByIdUtilisateur().getIdUtilisateur() == idUtilisateur) {
                    myFactureIds.add(facture.getIdFacture());
                }
            }

            myFactureList = factureList.stream().filter(f ->
                    myFactureIds.contains(f.getIdFacture())
            ).collect(Collectors.toList());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        finally {
            if (logger.isInfoEnabled()) {
                logger.info("Fermeture de l'EntityManager");
            }
            em.close();
        }

        request.setAttribute("factureList", myFactureList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/facture.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
